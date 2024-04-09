package com.anranruozhu.entity;

import lombok.Data;
import lombok.NonNull;

/**
 * @author anranruozhu
 * @ClassName UserINfo
 * @Description 用户的个人信息
 * @create 2024/4/7 15:37
 **/
@Data
public class UserInfo {
    private String name;
    @NonNull
    private String password;
    @NonNull
    private String phoneNumber;
    private String email;
    private String address;
}
