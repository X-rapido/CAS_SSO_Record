
### build.sh 文件内容简单介绍

文件内容很容易被理解

#### 1、help函数

```basic
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
```

翻译成中文意思大致如下

```basic
function help() {
	echo "Usage: build.sh [copy|clean|package|run|debug|bootrun|gencert]"
	echo "	copy: 从 ./etc/cas/config 中拷贝文件到 /etc/cas/config"
	echo "	clean: 清理Maven构建目录"
	echo "	package: 清理并重新构建 CAS war"
	echo "	run: 通过 spring boot 构建并运行 cas.war (java -jar target/cas.war)"
	echo "	runalone: 构建并运行 cas.war on its own (target/cas.war)"
	echo "	debug: 运行CAS.war并在端口5000上侦听Java调试器"
	echo "	bootrun: 使用maven spring启动插件运行，不能用于多个依赖项"
	echo "	gencert: 在默认情况下CAS所在的位置创建带有SSL证书的密钥库"
	echo "	cli: Run the CAS command line shell and pass commands"
}
```

#### 2、copy函数
将cas-server中的 etc/cas 文件夹内容，拷贝到电脑到 /etc/cas 文件夹下

```basic
function copy() {
	echo -e "Creating configuration directory under /etc/cas"
	mkdir -p /etc/cas/config

	echo -e "Copying configuration files from etc/cas to /etc/cas"
	cp -rfv etc/cas/* /etc/cas
}
```

### 3、clean函数
如果你电脑上配置了maven
使用 build.sh clean 等同于 mvn clean
```basic
function clean() {
	./mvnw clean "$@"
}
```

#### 4、package函数 含义同 clean函数


#### 5、gencert函数
调用keytool命令，在etc文件夹中，生成SSL到密钥文件

```basic
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
```
