package com.anranruozhu.entry;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author anranruozhu
 * @ClassName UserINfo
 * @Description 用户的个人信息
 * @create 2024/4/7 15:37
 **/
@Data
public class UserInfo {
    private String name;
    private String password;
    private String phoneNumber;
}
