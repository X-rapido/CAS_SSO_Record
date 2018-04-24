package com.tingfeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tingfeng.mapper")
public class AppRun {

	public static void main(String[] args) {
		SpringApplication.run(AppRun.class, args);
	}
}
