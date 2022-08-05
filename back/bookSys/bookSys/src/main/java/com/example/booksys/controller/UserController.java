package com.example.booksys.controller;


import com.example.booksys.BookSysApplication;
import com.example.booksys.entity.User;
import com.example.booksys.mapper.UserMapper;
import com.example.booksys.service.impl.UserServiceImpl;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Delete;
import com.example.booksys.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author XuDaxia
 * @since 2022-07-31
 */
@RestController
@RequestMapping("/login")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserServiceImpl userService;

    public UserController() {
    }

    @RequestMapping
    public Result login(@RequestParam(value = "ID",required = true) String ID,
                        @RequestParam(value = "password",required = true)String password,
                        @RequestParam(value = "type",required = true)Integer type) {
        return  userService.login(ID, password, type);
    }
}

