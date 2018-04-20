package com.tingfeng.controller;

import com.tingfeng.domain.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class DemoController {

    @RequestMapping("/world")
    public String world() {
        return "Client 2 后端 api/world 接口";
    }

    @GetMapping("/users")
    public List<User> users() {

        List<User> userList = Arrays.asList(
                new User("X-rapido", "123456", "男", 23),
                new User("听风", "123456", "女", 22),
                new User("程序喵", "123456", "女", 22)
        );

        return userList;
    }

    @GetMapping("/books")
    public List<String> books() {
        return Arrays.asList("《Java 编程高手之旅》", "《Python 3编程高手之旅》", "《MongoDB 编程高手之旅》", "《MySQL 编程高手之旅》");
    }

}
