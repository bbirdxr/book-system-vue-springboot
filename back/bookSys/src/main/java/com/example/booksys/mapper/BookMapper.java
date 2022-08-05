package com.example.booksys.mapper;

import com.example.booksys.entity.Book;
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
public interface BookMapper extends BaseMapper<Book> {
    @Select("select * from book")
    List<Book> findAllBook();

    @Insert("insert into book(bookId,bookName,type,author,price,language) values(#{bookId},#{bookName},#{type},#{author},#{price},#{language})")
    Integer addbook(Book book);

    @Update("update book set bookName = #{bookName}, type = #{type}, author = #{author}, price = #{price}, language = #{language} where bookId = #{bookId}")
    Integer moditybook(Book book);

    @Delete("delete from book where bookId = #{bookId}")
    Integer deleteBookByid(Integer bookId);
}
