package com.tingfeng.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){
        return " index 接口";
    }

    @RequestMapping("/world")
    public String world(){
        return " world 接口";
    }


}
