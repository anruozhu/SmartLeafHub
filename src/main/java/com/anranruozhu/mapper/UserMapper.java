package com.anranruozhu.mapper;

import com.anranruozhu.entry.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    boolean findByPhone(String phone);
    User login(String phone, String password);
    void register(String phone,String password);
}
