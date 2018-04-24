
**注意要点**  
- 名字和姓氏输入项：访问的域名地址
- alias：别名可以随便写，一般要有意义，后续操作别名要一致，我这里保持和域名统一了

### 证书生成与导入

#### 1. 使用java自带keytool创建本地密钥库
- 密码：changeit
- 别名：cas.server.com

> **语法：** keytool -genkey -alias cas.server.com -keyalg RSA -keystore casServer.keystore

```basic
➜  cas keytool -genkey -alias cas.server.com -keyalg RSA -keystore casServer.keystore
输入密钥库口令:  
再次输入新口令:
您的名字与姓氏是什么?
  [Unknown]:  cas.server.com
您的组织单位名称是什么?
  [Unknown]:  wusong
您的组织名称是什么?
  [Unknown]:  yanfazu
您所在的城市或区域名称是什么?
  [Unknown]:  beijing
您所在的省/市/自治区名称是什么?
  [Unknown]:  dongcheng
该单位的双字母国家/地区代码是什么?
  [Unknown]:  zh
CN=cas.server.com, OU=wusong, O=yanfazu, L=beijing, ST=dongcheng, C=zh是否正确?
  [否]:  y

输入 <cas.server.com> 的密钥口令
	(如果和密钥库口令相同, 按回车):  
再次输入新口令:

Warning:
JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore casServer.keystore -destkeystore casServer.keystore -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
```


#### 2. 把密钥库导出成证书文件

> **语法：** keytool -export -alias cas.server.com -keystore casServer.keystore -file casServer.crt -storepass changeit

```basic
➜  cas keytool -export -alias cas.server.com -keystore casServer.keystore -file casServer.crt -storepass changeit
存储在文件 <casServer.crt> 中的证书

Warning:
JKS 密钥库使用专用格式。建议使用 "keytool -importkeystore -srckeystore casServer.keystore -destkeystore casServer.keystore -deststoretype pkcs12" 迁移到行业标准格式 PKCS12。
```

#### 3. 查看证书

> **语法：** keytool -printcert -file casServer.crt

```basic
➜  cas keytool -printcert -file casServer.crt
所有者: CN=cas.server.com, OU=wusong, O=yanfazu, L=beijing, ST=dongcheng, C=zh
发布者: CN=cas.server.com, OU=wusong, O=yanfazu, L=beijing, ST=dongcheng, C=zh
序列号: 1e2220da
有效期为 Thu Mar 08 15:46:33 CST 2018 至 Wed Jun 06 15:46:33 CST 2018
证书指纹:
	 MD5:  XXXXXXXXXXXXXXXXXX
	 SHA1: XXXXXXXXXXXXXXXXXX
	 SHA256: XXXXXXXXXXXXXXXXXX
签名算法名称: SHA256withRSA
主体公共密钥算法: 2048 位 RSA 密钥
版本: 3

扩展:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: AE C3 88 BB 9B 2B 44 6E   BC B1 EB 57 82 24 62 6A  .....+Dn...W.$bj
0010: CC F6 31 CF                                        ..1.
]
]
```

#### 4. 将创建过的证书导入到java证书库

> **语法：** sudo keytool -import -keystore "/Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/security/cacerts" -file "/Users/liurenkui/Desktop/cas/casServer.crt" -alias cas.server.com


这里建议把进入caserts目录再操作，不然容易出现如下错误，大概是无法识别，另外需要加上root权限。

```basic
keytool 错误: java.io.FileNotFoundException: /Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/jre/lib/security/cacerts (No such file or directory)
```

实例

```basic
➜  security sudo keytool -import -keystore cacerts -file "/Users/liurenkui/Desktop/cas/casServer.crt" -alias cas.server.com
Password:
输入密钥库口令:  
所有者: CN=cas.server.com, OU=wusong, O=yanfazu, L=beijing, ST=dongcheng, C=zh
发布者: CN=cas.server.com, OU=wusong, O=yanfazu, L=beijing, ST=dongcheng, C=zh
序列号: 1e2220da
有效期为 Thu Mar 08 15:46:33 CST 2018 至 Wed Jun 06 15:46:33 CST 2018
证书指纹:
	 MD5:  XXXXXXXXXXXXXXXXXX
	 SHA1: XXXXXXXXXXXXXXXXXX
	 SHA256: XXXXXXXXXXXXXXXXXX
签名算法名称: SHA256withRSA
主体公共密钥算法: 2048 位 RSA 密钥
版本: 3

扩展:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: AE C3 88 BB 9B 2B 44 6E   BC B1 EB 57 82 24 62 6A  .....+Dn...W.$bj
0010: CC F6 31 CF                                        ..1.
]
]

是否信任此证书? [否]:  y
证书已添加到密钥库中
➜  security
```

#### 5. 查看JDK证书内容

> **语法：** keytool -list -v -keystore cacerts -alias cas.server.com

导入成功之后，jdk证书文件会有对应的如下内容

``` basic
➜  security keytool -list -v -keystore cacerts -alias cas.server.com
输入密钥库口令:  
别名: cas.server.com
创建日期: 2018-3-8
条目类型: trustedCertEntry

所有者: CN=cas.server.com, OU=wusong, O=yanfazu, L=beijing, ST=dongcheng, C=zh
发布者: CN=cas.server.com, OU=wusong, O=yanfazu, L=beijing, ST=dongcheng, C=zh
序列号: 1e2220da
有效期为 Thu Mar 08 15:46:33 CST 2018 至 Wed Jun 06 15:46:33 CST 2018
证书指纹:
	 MD5:  XXXXXXXXXXXXXXXXXX
	 SHA1: XXXXXXXXXXXXXXXXXX
	 SHA256: XXXXXXXXXXXXXXXXXX
签名算法名称: SHA256withRSA
主体公共密钥算法: 2048 位 RSA 密钥
版本: 3

扩展:

#1: ObjectId: 2.5.29.14 Criticality=false
SubjectKeyIdentifier [
KeyIdentifier [
0000: AE C3 88 BB 9B 2B 44 6E   BC B1 EB 57 82 24 62 6A  .....+Dn...W.$bj
0010: CC F6 31 CF                                        ..1.
]
]

➜  security

```
#### 6. 根据 alias 别名删除 JDK 证书

> **语法：** sudo keytool -delete -alias cas.server.com -keystore cacerts

```basic
➜  security sudo keytool -delete -alias cas.server.com -keystore cacerts
Password:
输入密钥库口令:  
```

#### 7. 浏览器访问

配置完成证书之后，访问浏览器查看证书如下图效果

![image](/assets/images/certificate.png)






****
