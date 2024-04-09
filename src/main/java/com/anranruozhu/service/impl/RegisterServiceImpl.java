package com.anranruozhu.service.impl;

import com.anranruozhu.common.Result;
import com.anranruozhu.mapper.UserMapper;
import com.anranruozhu.service.RegisterService;
import com.anranruozhu.utils.MD5;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName RegisterServiceImpl
 * @Description 注册服务的实现
 * @create 2024/4/9 上午9:33
 **/
@Data
@Component
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Result register(String phone, String password) {
        String pwd = MD5.md5(password, phone);
        userMapper.register(phone,pwd);
        result.setCode(200);
        result.setData("注册成功");
        result.setMsg("ok");
        return result;
    }
}
