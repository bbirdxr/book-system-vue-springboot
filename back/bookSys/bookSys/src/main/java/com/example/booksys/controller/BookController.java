package com.example.booksys.controller;


import com.example.booksys.common.Result;
import com.example.booksys.entity.Book;
import com.example.booksys.mapper.BookMapper;
import com.example.booksys.service.impl.BookServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public Result deletebookbyid(@RequestParam(value = "bookId",required = true) Integer id)
    {
        return bookService.deletebookbyid(id);
    }

    @PostMapping("/search")
    public Result findbookbyidnametype(@RequestParam(value = "currentPage",required = true) Integer currentPage,
                                           @RequestParam(value = "pageSize",required = true) Integer pageSize,
                                           @RequestBody(required = false) Map<String,String> search)
    {
        return  bookService.findbookbyidnametype(currentPage,pageSize,search);
    }
}

