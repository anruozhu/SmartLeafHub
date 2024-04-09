package com.anranruozhu.mapper;

import com.anranruozhu.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int UpdatePassword(String phone,String password);
    String findByPhone(String phone);
    User login(String phone, String password);
    void register(String phone,String password);
}
