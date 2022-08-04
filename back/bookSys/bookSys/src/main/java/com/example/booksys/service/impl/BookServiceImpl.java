package com.example.booksys.service.impl;

import com.example.booksys.common.Result;
import com.example.booksys.entity.Book;
import com.example.booksys.entity.User;
import com.example.booksys.mapper.BookMapper;
import com.example.booksys.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Autowired
    private BookMapper bookMapper;
    @Override
    public Result findallbook() {
        List<Book> allbooks = bookMapper.findAllBook();
        return Result.success(allbooks);
    }
    @Override
    public Result addbook(Book book) {
            bookMapper.addbook(book);
            return Result.success();
    }

    @Override
    public Result modifybook(Book book) {
        boolean ifhavebook = false;
        List<Book> allbook = bookMapper.findAllBook();
        for (Book onebook:allbook)
        {
            if(onebook.getBookId().equals(book.getBookId()))
            {
                ifhavebook = true;
                break;
            }
        }
        if(ifhavebook)
        //修改
        {
            bookMapper.moditybook(book);
            return  Result.success();
        }
        else//无此书
        {
            return  Result.error("400","参数错误");
        }
    }

    @Override
    public Result deletebookbyid(Integer id) {
        boolean ifhavebook = false;
        List<Book> allbook = bookMapper.findAllBook();
        for (Book onebook:allbook)
        {
            if(onebook.getBookId().equals(id))
            {
                ifhavebook = true;
                break;
            }
        }
        if(ifhavebook)
        //删除
        {
            bookMapper.deleteBookByid(id);
            return  Result.success();
        }
        else//无此书
        {
            return  Result.error("400","参数错误");
        }
    }

    @Override
    public List<Book> findbookbyidnametype(Integer bookId, String bookName, Integer bookType) {//改
        List<Book> returnbook = null;
        List<Book> allbook = bookMapper.findAllBook();
        for (Book onebook:allbook)
        {
            if(onebook.getBookId().equals(bookId) || onebook.getBookName().equals(bookName) || onebook.getType().equals(bookType))
            {
                returnbook.add(onebook);
            }
        }
        return returnbook;
    }
}
