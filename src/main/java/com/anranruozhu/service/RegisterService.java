package com.anranruozhu.service;

import com.anranruozhu.common.Result;
import com.anranruozhu.entry.User;
import com.anranruozhu.entry.UserInfo;
import com.anranruozhu.mapper.UserMapper;
import com.anranruozhu.utils.MD5;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName RegisterService
 * @Description 注册服务类
 * @create 2024/4/7 10:29
 **/
@Data
@Component
public class RegisterService {
    @Autowired
    private UserMapper userMapper;
    Result result=new Result();
    public Result register(UserInfo userinfo) {
        String pwd = MD5.md5(userinfo.getPassword(), userinfo.getPhoneNumber());
        userMapper.register(userinfo.getPhoneNumber(),pwd);
        result.setCode(200);
        result.setData("注册成功");
        result.setMsg("ok");
    return result;
    }
}
