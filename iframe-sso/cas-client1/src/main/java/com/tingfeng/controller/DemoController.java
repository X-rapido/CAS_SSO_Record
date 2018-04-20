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
    public String world(){
        return "Client 1 后端 api/world 接口";
    }

    @GetMapping("/users")
    public List<User> users() {

        List<User> userList = Arrays.asList(
                new User("听风", "123456", "男", 23),
                new User("阿敏", "123456", "女", 22)
        );

        return userList;
    }

    @GetMapping("/books")
    public List<String> books() {
        return Arrays.asList("《你今天真好看》","《我能咬你一口吗？》","《人性的弱点》","《原则》");
    }

}
