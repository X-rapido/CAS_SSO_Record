package com.tingfeng.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @GetMapping("/books")
    public List<String> books() {
        return Arrays.asList("《Java 编程高手之旅》", "《Python 3编程高手之旅》", "《MongoDB 编程高手之旅》", "《MySQL 编程高手之旅》");
    }

}
