package com.tingfeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "自定义 SSO-Server 项目，用于和 CAS-Server 进行交互";
    }

}
