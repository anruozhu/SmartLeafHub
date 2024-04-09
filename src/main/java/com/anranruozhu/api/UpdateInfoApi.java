package com.anranruozhu.api;

import com.anranruozhu.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anranruozhu
 * @ClassName UpdateInfoApi
 * @Description 对用户信息的修改
 * @create 2024/4/8 下午5:15
 **/
@RestController
@RequestMapping("/UserInfo")
public class UpdateInfoApi {
    @Autowired
    private UserInfoMapper userInfoMapper;
    //@PostMapping("/update")

}
