package com.anranruozhu.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    void login(String username,String password);
    void register(String username,String password,String phone);
}
