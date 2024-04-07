package com.anranruozhu.api;

import com.anranruozhu.common.Result;
import com.anranruozhu.entry.User;
import com.anranruozhu.entry.UserInfo;
import com.anranruozhu.service.LoginService;
import com.anranruozhu.service.RegisterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author anranruozhu
 * @ClassName AuthController
 * @Description 身份验证接口
 * @create 2024/4/7 11:12
 **/
@Slf4j
@RestController
public class AuthController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    // 登录接口
    @PostMapping("/login")
    public Result login(@RequestParam String phone, @RequestParam String password) {
        return loginService.login(phone,password);
    }
    // 注册接口
    @PostMapping("/register")
    public Result register(@RequestBody UserInfo userinfo) {
        // 处理注册逻辑
        // 返回注册结果
        log.info(userinfo.getPhoneNumber()+userinfo.getPassword());
        return registerService.register(userinfo);
    }
}
