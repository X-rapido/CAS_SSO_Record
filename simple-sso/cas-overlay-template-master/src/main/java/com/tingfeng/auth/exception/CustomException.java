package com.tingfeng.auth.exception;

import javax.security.auth.login.AccountExpiredException;

/**
 * 自定义异常类
 */
public class CustomException extends AccountExpiredException{

    public CustomException() {
        super();
    }

    public CustomException(String msg) {
        super(msg);
    }
}
