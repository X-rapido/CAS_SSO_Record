package com.tingfeng.controller;

import com.google.gson.Gson;
import com.tingfeng.domain.User;
import com.tingfeng.model.request.UserLogin;
import com.tingfeng.utils.HttpProxy;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login")
    public String login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String service = request.getParameter("service");

        System.out.println(username + ":" + password + ":" + service);

        String param = new Gson().toJson(new UserLogin(username, password, service));

        // 1、获取SSO端请求的ST
        String st = HttpProxy.httpRequest("http://sso.server.com:9000/sso/user/login", param, HttpMethod.POST);

        // 2、302重定向最后授权
        String redirectUrl = service + "?ticket=" + st;
        System.out.println("redirectUrl:" + redirectUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/users")
    @ResponseBody
    public List<User> users() {

        List<User> userList = Arrays.asList(
                new User("听风", "123456", "男", 23),
                new User("阿敏", "123456", "女", 22)
        );

        return userList;
    }


}
