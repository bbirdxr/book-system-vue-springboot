package com.example.booksys.service;

import com.example.booksys.common.Result;
import com.example.booksys.entity.Book;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.booksys.entity.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author XuDaxia
 * @since 2022-07-31
 */
public interface IBookService extends IService<Book> {

    public Result findallbook();
    public Result addbook(Book book);

    public Result modifybook(Book book);

    public Result deletebookbyid(Integer id);
    public Result findbookbyidnametype(Integer currentPage, Integer pageSize, Map<String,String> search);
}
