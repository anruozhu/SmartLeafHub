package com.anranruozhu.mapper;

import com.anranruozhu.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    int InsertID(int id);
    int UpdateUserInfo(UserInfo userInfo);
}
