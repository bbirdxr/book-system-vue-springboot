package com.example.booksys.controller;


import com.example.booksys.common.Result;
import com.example.booksys.entity.Book;
import com.example.booksys.mapper.BookMapper;
import com.example.booksys.service.impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/bookinfo")
public class BookController {
    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookServiceImpl bookService;

    @RequestMapping("/findallbook")
    public Result findallbook()
    {
        return bookService.findallbook();
    }

    @PostMapping("/add")
    public Result addbook(@RequestBody Book book)
    {
        return bookService.addbook(book);
    }

    @PostMapping("/modify")
    public Result modifybook(@RequestBody Book book)
    {
        return bookService.modifybook(book);
    }

    @RequestMapping("/delete")
    public Result deletebookbyid(@RequestParam("id") Integer id)
    {
        return bookService.deletebookbyid(id);
    }

    @RequestMapping("/search")//(改，需分页）
    public List<Book> findbookbyidnametype(@RequestParam(value = "currentpage",required = true) Integer currentPage,
                                           @RequestParam(value = "pagesize",required = true) Integer pageSize,
                                           @RequestParam(value = "bookId",required = false,defaultValue = "-1") Integer bookId,
                                           @RequestParam(value = "bookName",required = false,defaultValue = "-1") String bookName,
                                           @RequestParam(value = "booktype",required = false,defaultValue = "-1") Integer bookType)
    {
        Integer returnid = (currentPage-1)*pageSize + bookId;
        return  bookService.findbookbyidnametype(returnid,bookName,bookType);
    }
}

