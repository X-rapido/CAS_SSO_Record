package com.tingfeng.controller;

import com.google.gson.Gson;
import com.tingfeng.domain.User;
import com.tingfeng.utils.HttpClientProxyUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HelloController {

    private static String API_BASE_URL = "http://client1.com:8888/api";

    @GetMapping("/hello")
    public String hello() {
        return "前端 Hello 接口响应";
    }

    @GetMapping("/world")
    public String world() {

        String result = HttpClientProxyUtil.sendGet(API_BASE_URL + "/world", "");
        System.out.println("Client1 接口响应结果：" + result);

        return result;
    }

    @GetMapping("/users")
    public List<User> users() {
        String result = HttpClientProxyUtil.sendGet(API_BASE_URL + "/users", "");
        System.out.println("Client1 接口响应结果：" + result);

        if (null != result && !result.equals("")) {
            Gson gson = new Gson();
            List<User> userList = gson.fromJson(result, List.class);
            return userList;
        }

        return null;
    }

    @GetMapping("/books")
    public List<String> books() {

        String result = HttpClientProxyUtil.sendGet(API_BASE_URL + "/books", "");
        System.out.println("Client1 接口响应结果：" + result);

        if (null != result && !result.equals("")) {
            Gson gson = new Gson();
            List<String> nameList = gson.fromJson(result, List.class);
            return nameList;
        }

        return null;
    }

}
