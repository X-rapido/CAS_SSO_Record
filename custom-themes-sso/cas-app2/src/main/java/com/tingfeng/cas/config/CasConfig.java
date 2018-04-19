package com.tingfeng.cas.config;

/**
 * Cas 的一些配置项
 */
public class CasConfig {

    /**
     * 当前应用程序的baseUrl（注意最后面的斜线）
     */
    public static String SERVER_NAME = "http://app2.com:8282/";

    /**
     * App2 登出成功url
     */
    public static String APP_LOGOUT_PATH = SERVER_NAME + "user/logout/success";

    /**
     * CAS服务器地址
     */
    public static String CAS_SERVER_PATH = "https://cas.server.com:8443/cas";

    /**
     * CAS登陆服务器地址
     */
    public static String CAS_SERVER_LOGIN_PATH = "https://cas.server.com:8443/cas/login";

    /**
     * CAS登出服务器地址
     */
    public static String CAS_SERVER_LOGOUT_PATH = "https://cas.server.com:8443/cas/logout";


}
