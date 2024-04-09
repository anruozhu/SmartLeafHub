package com.anranruozhu.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    int InsertID(int id);
    int UpdateNickName(int id ,String name);
    int UpdateEmail(String email);
    int UpdateAddress(String address);
}
