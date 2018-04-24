package com.tingfeng;


import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 自定义加密类
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    public String encode(CharSequence password) {
        try {
            //给数据进行md5加密
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.toString().getBytes());
            String pwd = new BigInteger(1, md.digest()).toString(16);
            System.out.println("encode方法：加密前（" + password + "），加密后（" + pwd + "）");
            return pwd;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 调用这个方法来判断密码是否匹配
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodePassword) {
        // 判断密码是否存在
        if (rawPassword == null) {
            return false;
        }

        //通过md5加密后的密码
        String pass = this.encode(rawPassword.toString());

        System.out.println("matches方法：rawPassword：" + rawPassword + "，encodePassword：" + encodePassword + "，pass：" + pass);
        //比较密码是否相等的问题
        return pass.equals(encodePassword);
    }
}
