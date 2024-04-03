package com.anranruozhu.entry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author anranruozhu
 * @ClassName User
 * @Description 用户
 * @create 2024/4/3 15:47
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel
public class User {
    //实体类中，Integer类型的属性加@ApiModelProperty时，必须要给example参数赋值，且值必须为数字类型。
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "用户密码")
    private String password;
    //getter/setter用@Data注解自动生成
}