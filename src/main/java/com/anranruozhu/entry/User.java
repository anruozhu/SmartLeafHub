package com.anranruozhu.entry;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;

/**
 * @author anranruozhu
 * @ClassName User
 * @Description 用户
 * @create 2024/4/3 15:47
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    private int id;
    private String username;
    private String password;
    private String phoneNumber;
    //getter/setter用@Data注解自动生成
}