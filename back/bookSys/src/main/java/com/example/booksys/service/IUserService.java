package com.example.booksys.service;

import com.example.booksys.common.Result;
import com.example.booksys.entity.Book;
import com.example.booksys.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuDaxia
 * @since 2022-07-31
 */
public interface IUserService extends IService<User> {
    public Result login(String ID, String password, Integer type);
}
