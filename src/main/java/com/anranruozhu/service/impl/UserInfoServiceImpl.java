package com.anranruozhu.service.impl;

import com.anranruozhu.common.Result;
import com.anranruozhu.entity.UserInfo;
import com.anranruozhu.mapper.UserInfoMapper;
import com.anranruozhu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName UserInfoServiceImpl
 * @Description 对用户信息的服务管理的实现类
 * @create 2024/4/9 上午10:19
 **/
@Component
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public Result UpdateUserInfo(UserInfo userInfo) {
        return null;
    }
}
