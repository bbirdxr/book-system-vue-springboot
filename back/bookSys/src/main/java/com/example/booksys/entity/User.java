package com.example.booksys.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>+-
 *
 * @author XuDaxia
 * @since 2022-07-31
 */
@Getter
@Setter
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    private String ID;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("身份")//0:管理者,1:用户
    private int type;


}
