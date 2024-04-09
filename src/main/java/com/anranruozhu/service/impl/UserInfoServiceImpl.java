package com.anranruozhu.service.impl;

import com.anranruozhu.common.Result;
import com.anranruozhu.entity.UserInfo;
import com.anranruozhu.mapper.UserInfoMapper;
import com.anranruozhu.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName UserInfoServiceImpl
 * @Description 对用户信息的服务管理的实现类
 * @create 2024/4/9 上午10:19
 **/
@Component
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Override
    public Result UpdateUserInfo(UserInfo userInfo) {
        try {
            userInfoMapper.UpdateUserInfo(userInfo);
        }catch(Exception e){
            log.error(e.getMessage());
            throw new RuntimeException("用户个人信息保存失败");
        }
        Result res = new Result();
        res.setCode(200);
        res.setMsg("用户个人信息保存成功");
        res.setData(userInfo);
        return res;
    }
}
