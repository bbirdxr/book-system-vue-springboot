package com.example.booksys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author XuDaxia
 * @since 2022-07-31
 */
@Getter
@Setter
@ApiModel(value = "Book对象", description = "")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
      @TableId(value = "bookId", type = IdType.AUTO)
    private Integer bookId;

    @ApiModelProperty("书名")
    private String bookName;

    @ApiModelProperty("类型")
    private Integer type;

    @ApiModelProperty("作者")
    private String author;

    @ApiModelProperty("价格")
    private Float price;

    @ApiModelProperty("语言")
    private String language;


}
