package com.anranruozhu.api;

import com.anranruozhu.common.Result;
import com.anranruozhu.service.LoginService;
import com.anranruozhu.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anranruozhu
 * @ClassName AuthController
 * @Description 身份验证接口
 * @create 2024/4/7 11:12
 **/
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
    public String register(@RequestParam String username, @RequestParam String password, @RequestParam String phoneNumber) {
        // 处理注册逻辑
        // 返回注册结果
        return "Registration successful for user: " + username;
    }

    @GetMapping("/secure/info")
    public String info(){
        return "通过token验证";
    }
}
