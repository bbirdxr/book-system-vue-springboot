package com.example.booksys.mapper;

import com.example.booksys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author XuDaxia
 * @since 2022-07-31
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("select * from user")
    List<User> findAllUser();

    @Insert("insert into user(ID,password,type) values(#{ID},#{password},#{type})")
    Integer addUser(User user);
}
