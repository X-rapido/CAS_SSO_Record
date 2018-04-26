
### CAS SSO 单点登录记录

- **作者：刘仁奎**
- **个人网址**
  - 程序喵：http://www.ibloger.net / http://www.chengxumiao.net
  - 程序员购书导航指南：http://books.chengxumiao.net
  - QQ：1056856191

GitHub地址：https://github.com/X-rapido/CAS_SSO_Record

> 以下内容根据自己的理解编写，有不正确的地方，还望朋友们及时告知，并给出解决方案，感激不尽！

### 框架说明

- Maven 或 Gradle
- springboot 1.5.10
- cas-server 5.2.2
- cas-client 3.5.0

### 视频演示

- CAS 5.1.2 基本的演示，说明和安装过程：https://v.qq.com/x/page/j063197nnmj.html
- CAS 5.2.x 单点登录 —— 实现单点登录演示：http://v.qq.com/x/page/d063304k06a.html
- Cas 5.2.x 单点登录使用自定义主题 - 演示：http://v.qq.com/x/page/v0633n7q9eu.html
- CAS 5.2.x 单点登录 —— Iframe嵌入方式演示：http://v.qq.com/x/page/p0614wjt2gy.html
- CAS 5.2.x 单点登录 —— 代理认证演示：http://v.qq.com/x/page/y0614dn6mpr.html
- CAS 5.2.x 单点登录 —— Restful API方式 ：http://v.qq.com/x/page/w0614c07580.html
- Cas 5.2.x 使用 —— 通过邮箱找回用户密码: https://v.qq.com/x/page/e06362scktw.html
- Cas 5.2.x 使用 —— Management 服务管理登录Demo: https://v.qq.com/x/page/l063881p938.html

### 项目目录

```markdown
├── README.md
├── change-static-password
│   └── cas-overlay-template-master
├── custom-password-verification
│   └── cas-overlay-template-master
├── custom-themes-sso
│   ├── cas-app1
│   ├── cas-app2
│   └── cas-overlay-template-master
├── email-reset-password
│   └── cas-overlay-template-master
├── embed-tomcat-run
│   └── cas-overlay-template-master
├── iframe-sso
│   ├── cas-app1
│   ├── cas-app2
│   ├── cas-client1
│   ├── cas-client2
│   └── cas-overlay-template-master
├── jdbc-sso
│   └── cas-overlay-template-master
├── outer-tomcat-run
│   └── server.xml
├── proxy-sso
│   ├── cas-overlay-template-master
│   ├── client1
│   └── client2
├── rest-sso
│   ├── cas-overlay-template-master
│   └── cas_db
├── restful-api-sso
│   ├── cas-app1
│   ├── cas-app2
│   ├── cas-client1
│   ├── cas-client2
│   ├── cas-overlay-template-master
│   └── sso-server
├── simple-sso
│   ├── cas-app1
│   ├── cas-app2
│   └── cas-overlay-template-master
├── st-storage-redis
└── yml-config
    └── cas-overlay-template-master
```

目录     | 文档
-------- | ---
embed-tomcat-run | [Cas 5.2.x版本单点登录服务安装 —— SpringBoot内部运行（一）](http://www.ibloger.net/article/3114.html)
outer-tomcat-run | [Cas 5.2.x版本单点登录服务安装 —— 外部Tomcat运行（二）](http://www.ibloger.net/article/3115.html)
change-static-password | [Cas 5.2.x版本使用 —— 修改默认的用户名和密码（三）](http://www.ibloger.net/article/3116.html)
yml-config | [Cas 5.2.x版本使用 —— 通过yml的方式配置（四）](http://www.ibloger.net/article/3118.html)
jdbc-sso |  [Cas 5.2.x版本使用 —— 单点登录JDBC认证（五）](http://www.ibloger.net/article/3119.html)
custom-password-verification | [Cas 5.2.x版本使用 —— 自定义密码验证（六）](http://www.ibloger.net/article/3123.html)
rest-sso | [Cas 5.2.x版本使用 —— 单点登录自定义REST认证（七）](http://www.ibloger.net/article/3120.html)
simple-sso | [Cas 5.2.x版本使用 —— 实现SSO单点登录（九）](http://www.ibloger.net/article/3126.html)
custom-themes-sso | [Cas 5.2.x版本使用 —— 自定义登录界面 / 自定义主题风格（十二）](http://www.ibloger.net/article/3125.html)
iframe-sso | [Cas 5.2.x版本使用 —— 客户端使用iframe嵌套方式实现SSO（十三）](http://www.ibloger.net/article/3128.html)
proxy-sso | [Cas 5.2.x版本使用 —— 代理认证实现SSO（十四）](http://www.ibloger.net/article/3129.html)
restful-api-sso | [Cas 5.2.x版本使用 —— 配置 Swagger API 集成（十九）](http://www.ibloger.net/article/3136.html)
email-reset-password | [Cas 5.2.x版本使用 —— 通过邮箱重置用户密码（二十）](http://www.ibloger.net/article/3137.html)
cas-management-run | [Cas 5.2.x版本使用 —— Management 服务管理搭建（二十二）](http://www.ibloger.net/article/3139.html)

### 教程目录

- [Cas 5.2.x版本单点登录服务安装 —— SpringBoot内部运行（一）](http://www.ibloger.net/article/3114.html)
- [Cas 5.2.x版本单点登录服务安装 —— 外部Tomcat运行（二）](http://www.ibloger.net/article/3115.html)
- [Cas 5.2.x版本使用 —— 修改默认的用户名和密码（三）](http://www.ibloger.net/article/3116.html)
- [Cas 5.2.x版本使用 —— 通过yml的方式配置（四）](http://www.ibloger.net/article/3118.html)
- [Cas 5.2.x版本使用 —— 单点登录JDBC认证（五）](http://www.ibloger.net/article/3119.html)
- [Cas 5.2.x版本使用 —— 自定义密码验证（六）](http://www.ibloger.net/article/3123.html)
- [Cas 5.2.x版本使用 —— 单点登录自定义REST认证（七）](http://www.ibloger.net/article/3120.html)
- [Cas 5.2.x版本使用 —— Debug调试源码（八）](http://www.ibloger.net/article/3124.html)
- [Cas 5.2.x版本使用 —— 实现SSO单点登录（九）](http://www.ibloger.net/article/3126.html)
- [Cas 5.2.x版本使用 —— 退出登录后跳转到指定页面（十）](http://www.ibloger.net/article/3047.html)
- [Cas 5.2.x版本使用 —— Service配置介绍（十一）](http://www.ibloger.net/article/3122.html)
- [Cas 5.2.x版本使用 —— 自定义登录界面 / 自定义主题风格（十二）](http://www.ibloger.net/article/3125.html)
- [Cas 5.2.x版本使用 —— 客户端使用iframe嵌套方式实现SSO（十三）](http://www.ibloger.net/article/3128.html)
- [Cas 5.2.x版本使用 —— 代理认证实现SSO（十四）](http://www.ibloger.net/article/3129.html)
- [Cas 5.2.x版本使用 —— 代理认证拓展理解（十五）](http://www.ibloger.net/article/3130.html)
- [Cas 5.2.x版本使用 —— 存储Ticket到redis（十六）](http://www.ibloger.net/article/3131.html)
- [Cas 5.2.x版本使用 —— Restful API 方式验证Ticket（十七）](http://www.ibloger.net/article/3133.html)
- [Cas 5.2.x版本使用 —— Restful API 方式实现SSO（十八）](http://www.ibloger.net/article/3135.html)
- [Cas 5.2.x版本使用 —— 配置 Swagger API 集成（十九）](http://www.ibloger.net/article/3136.html)
- [Cas 5.2.x版本使用 —— 通过邮箱重置用户密码（二十）](http://www.ibloger.net/article/3137.html)
- [Cas 5.2.x版本使用 —— 存储Service信息到数据库（二十一）](http://www.ibloger.net/article/3139.html)
- [Cas 5.2.x版本使用 —— Management 服务管理搭建（二十二）](http://www.ibloger.net/article/3139.html)












---
