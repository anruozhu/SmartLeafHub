package com.anranruozhu.service;

import com.anranruozhu.mapper.UserMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName LoginService
 * @Description 登陆业务类
 * @create 2024/4/7 10:28
 **/
@Data
@Component
public class LoginService {
    @Autowired
    private UserMapper userMapper;

}
