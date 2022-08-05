package com.example.booksys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@MapperScan("com.example.booksys.mapper")
public class BookSysApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookSysApplication.class, args);
    }

}
