package com.example.booksys.service.impl;

import com.example.booksys.common.Result;
import com.example.booksys.entity.User;
import com.example.booksys.mapper.UserMapper;
import com.example.booksys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author XuDaxia
 * @since 2022-07-31
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result login(String ID, String password, Integer type) {
        List<User> alluser = userMapper.findAllUser();
        for (User oneuser:alluser)
        {
            if(oneuser.getID().equals(ID) && oneuser.getPassword().equals(password) && oneuser.getType() == type)
            {
                userMapper.addUser(oneuser);
                return Result.success();
            }
        }
        return  Result.error("400","参数错误");
    }

}
