package com.tingfeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String index(){
        return "Client 1 后端 index 接口";
    }

    @RequestMapping("/world")
    public String world(){
        return "Client 1 后端 api/world 接口";
    }

}
