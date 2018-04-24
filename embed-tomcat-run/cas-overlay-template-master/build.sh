#!/bin/bash


function copy() {
	echo -e "Creating configuration directory under /etc/cas"
	mkdir -p /etc/cas/config

	echo -e "Copying configuration files from etc/cas to /etc/cas"
	cp -rfv etc/cas/* /etc/cas
}

function help() {
	echo "Usage: build.sh [copy|clean|package|run|debug|bootrun|gencert]"
	echo "	copy: Copy config from ./etc/cas/config to /etc/cas/config"
	echo "	clean: Clean Maven build directory"
	echo "	package: Clean and build CAS war, also call copy"
	echo "	run: Build and run cas.war via spring boot (java -jar target/cas.war)"
	echo "	runalone: Build and run cas.war on its own (target/cas.war)"
	echo "	debug: Run CAS.war and listen for Java debugger on port 5000"
	echo "	bootrun: Run with maven spring boot plugin, doesn't work with multiple dependencies"
	echo "	gencert: Create keystore with SSL certificate in location where CAS looks by default"
	echo "	cli: Run the CAS command line shell and pass commands"
}

function clean() {
	./mvnw clean "$@"
}

function package() {
	./mvnw clean package -T 5 "$@"
	copy
}

function bootrun() {
	./mvnw clean package spring-boot:run -T 5 "$@"
}

function debug() {
	package && java -Xdebug -Xrunjdwp:transport=dt_socket,address=5000,server=y,suspend=n -jar target/cas.war
}

function run() {
	package && java -jar target/cas.war
}

function runalone() {
	package && chmod +x target/cas.war && target/cas.war
}

function gencert() {
	if [[ ! -d /etc/cas ]] ; then 
		copy
	fi
	which keytool
	if [[ $? -ne 0 ]] ; then
		echo Error: Java JDK \'keytool\' is not installed or is not in the path
		exit 1
	fi
	# override DNAME and CERT_SUBJ_ALT_NAMES before calling or use dummy values
	DNAME="${DNAME:-CN=cas.example.org,OU=Example,OU=Org,C=US}"
	CERT_SUBJ_ALT_NAMES="${CERT_SUBJ_ALT_NAMES:-dns:example.org,dns:localhost,ip:127.0.0.1}"
	echo "Generating keystore for CAS with DN ${DNAME}"
	keytool -genkeypair -alias cas -keyalg RSA -keypass changeit -storepass changeit -keystore /etc/cas/thekeystore -dname ${DNAME} -ext SAN=${CERT_SUBJ_ALT_NAMES}
	keytool -exportcert -alias cas -storepass changeit -keystore /etc/cas/thekeystore -file /etc/cas/cas.cer
}

function cli() {
	
	CAS_VERSION=$(./mvnw -q -Dexec.executable="echo" -Dexec.args='${cas.version}' --non-recursive org.codehaus.mojo:exec-maven-plugin:1.3.1:exec 2>/dev/null)
	# echo "CAS version: $CAS_VERSION"
	JAR_FILE_NAME="cas-server-support-shell-${CAS_VERSION}.jar"
	# echo "JAR name: $JAR_FILE_NAME"
	JAR_PATH="org/apereo/cas/cas-server-support-shell/${CAS_VERSION}/${JAR_FILE_NAME}"
	# echo "JAR path: $JAR_PATH"

	JAR_FILE_LOCAL="$HOME/.m2/repository/$JAR_PATH";
	# echo "Local JAR file path: $JAR_FILE_LOCAL";
	if [ -f "$JAR_FILE_LOCAL" ]; then
		# echo "Using JAR file locally at $JAR_FILE_LOCAL"
		java -jar $JAR_FILE_LOCAL "$@"
		exit 0;
	fi

	DOWNLOAD_DIR=./target
	COMMAND_FILE="${DOWNLOAD_DIR}/${JAR_FILE_NAME}"
	if [ ! -f "$COMMAND_FILE" ]; then
		mkdir -p $DOWNLOAD_DIR
		./mvnw org.apache.maven.plugins:maven-dependency-plugin:3.0.2:get -DgroupId=org.apereo.cas -DartifactId=cas-server-support-shell -Dversion=$CAS_VERSION -Dpackaging=jar -DartifactItem.outputDirectory=$DOWNLOAD_DIR -DremoteRepositories=central::default::http://repo1.maven.apache.org/maven2,snapshots::::https://oss.sonatype.org/content/repositories/snapshots -Dtransitive=false
		./mvnw org.apache.maven.plugins:maven-dependency-plugin:3.0.2:copy -Dmdep.useBaseVersion=true -Dartifact=org.apereo.cas:cas-server-support-shell:$CAS_VERSION:jar -DoutputDirectory=$DOWNLOAD_DIR
	fi
	java -jar $COMMAND_FILE "$@"
	exit 0;

}

if [ $# -eq 0 ]; then
    echo -e "No commands provided. Defaulting to [run]\n"
    run
    exit 0
fi


case "$1" in
"copy")
    copy 
    ;;
"clean")
	shift
    clean "$@"
    ;;   
"package")
	shift
    package "$@"
    ;;
"bootrun")
	shift
    bootrun "$@"
    ;;
"debug")
    debug "$@"
    ;;
"run")
    run "$@"
    ;;
"runalone")
    runalone "$@"
    ;;
"gencert")
    gencert "$@"
    ;;
"cli")
    shift
    cli "$@"
    ;;
*)
    help
    ;;
esac