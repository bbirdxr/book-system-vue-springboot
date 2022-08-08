package com.example.booksys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.booksys.common.Result;
import com.example.booksys.entity.Book;
import com.example.booksys.entity.User;
import com.example.booksys.mapper.BookMapper;
import com.example.booksys.service.IBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            return  Result.error("400","查无此书");
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
            return  Result.error("400","查无此书");
        }
    }

    @Override
    public Result findbookbyidnametype(Integer currentPage, Integer pageSize, Map<String,String> search) {
//        POST http://localhost:8091/bookinfo/search?currentPage=1&pageSize=3
//        Content-Type: application/json
//
//        {
//            "bookId": "10002",
//                "bookName": "",
//                "bookType": "1"
//        }
        List<Book>allbook = bookMapper.findAllBook();
        boolean havebookId = true;
        boolean havebookName = true;
        boolean havebookType = true;
        if(search.get("bookId").equals(""))
        {
            havebookId = false;
        }
        if(search.get("bookName").equals(""))
        {
            havebookName = false;
        }
        if(search.get("bookType").equals(""))
        {
            havebookType = false;
        }

        /*example{
            {
                "bookId": "", "123", "123","123"
                "bookName": "", "", "123","123"
                "bookType": "", "", "",   123"
               if:
        }
        }*/
        LambdaQueryWrapper<Book> wrapper = Wrappers.<Book>lambdaQuery();
        if(!havebookId&&!havebookName&&!havebookType)
        {
            return Result.error("400","输入错误");
        }
        else if(havebookId&&!havebookName&&!havebookType)
        {
            boolean ifbookIdexist = false;
            for (Book onebook :allbook)
            {
                if(onebook.getBookId().equals(Integer.valueOf(search.get("bookId"))))
                {
                    ifbookIdexist = true;
                }
            }
            if(ifbookIdexist)
            {
                wrapper.eq(Book::getBookId,Integer.valueOf(search.get("bookId")));
            }
            else
            {
                return Result.error("400","输入错误");
            }
        }
        else if(!havebookId&&havebookName&&!havebookType)
        {
            boolean ifbookNameexist = false;
            for (Book onebook :allbook)
            {
                if(onebook.getBookName().equals(search.get("bookName")))
                {
                    ifbookNameexist = true;
                }
            }
            if(ifbookNameexist)
            {
                wrapper.eq(Book::getBookName,search.get("bookName"));
            }
            else
            {
                return Result.error("400","输入错误");
            }
        }
        else if(!havebookId&&!havebookName&&havebookType)
        {
            boolean ifbookTypeexist = false;
            for (Book onebook :allbook)
            {
                if(onebook.getType().equals(Integer.valueOf(search.get("bookType"))))
                {
                    ifbookTypeexist = true;
                }
            }
            if(ifbookTypeexist)
            {
                wrapper.eq(Book::getType,Integer.valueOf(search.get("bookType")));
            }
            else
            {
                return Result.error("400","输入错误");
            }
        }
        else if(havebookId&&havebookName&&!havebookType)
        {
            boolean ifexist = false;
            for (Book onebook :allbook)
            {
                if(onebook.getBookId().equals(Integer.valueOf(search.get("bookId")))&&onebook.getBookName().equals(search.get("bookName")))
                {
                    ifexist = true;
                }
            }
            if(ifexist)
            {
                wrapper.and(i->i
                        .eq(Book::getBookId,Integer.valueOf(search.get("bookId")))
                        .eq(Book::getBookName,search.get("bookName")));
            }
            else
            {
                return Result.error("400","输入错误");
            }
        }
        else if(!havebookId&&havebookName&&havebookType)
        {
            boolean ifexist = false;
            for (Book onebook :allbook)
            {
                if(onebook.getType().equals(Integer.valueOf(search.get("bookType")))&&onebook.getBookName().equals(search.get("bookName")))
                {
                    ifexist = true;
                }
            }
            if(ifexist)
            {
                wrapper.and(i->i
                        .eq(Book::getBookId,Integer.valueOf(search.get("bookType")))
                        .eq(Book::getBookName,search.get("bookName")));
            }
            else
            {
                return Result.error("400","输入错误");
            }
        }
        else if(havebookId&&!havebookName&&havebookType)
        {
            boolean ifexist = false;
            for (Book onebook :allbook)
            {
                if(onebook.getBookId().equals(Integer.valueOf(search.get("bookId")))&&onebook.getType().equals(Integer.valueOf(search.get("bookType"))))
                {
                    ifexist = true;
                }
            }
            if(ifexist)
            {
                wrapper.and(i->i
                        .eq(Book::getBookId,Integer.valueOf(search.get("bookId")))
                        .eq(Book::getType,Integer.valueOf(search.get("bookType"))));
            }
            else
            {
                return Result.error("400","输入错误");
            }
        }
        else
        {
            boolean ifexist = false;
            for (Book onebook :allbook)
            {
                if(onebook.getBookId().equals(Integer.valueOf(search.get("bookId")))&&onebook.getBookName().equals(search.get("bookName"))&&onebook.getType().equals(Integer.valueOf(search.get("bookType"))))
                {
                    ifexist = true;
                }
            }
            if(ifexist)
            {
                wrapper.and(i->i
                        .eq(Book::getBookId,Integer.valueOf(search.get("bookId")))
                        .eq(Book::getBookName,search.get("bookName"))
                        .eq(Book::getType,Integer.valueOf(search.get("bookType"))));
            }
            else
            {
                return Result.error("400","输入错误");
            }
        }
        Page<Book> bookPage = bookMapper.selectPage(new Page<>(currentPage,pageSize),wrapper);

        Result result  = Result.success(bookPage);
        result.setMessage("查询成功!");
        return result;

    }
}
