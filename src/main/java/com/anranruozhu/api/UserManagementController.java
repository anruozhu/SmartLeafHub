package com.anranruozhu.api;

import com.anranruozhu.common.Result;
import com.anranruozhu.entity.UserInfo;
import com.anranruozhu.service.LoginService;
import com.anranruozhu.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author anranruozhu
 * @ClassName UserManagementController
 * @Description 用户信息管理
 * @create 2024/4/9 下午5:49
 **/
@RestController
@RequestMapping("/secure")
@Slf4j
public class UserManagementController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @PostMapping("/SetUserInfo")
    public Result SetUserInfo(@RequestBody UserInfo userInfo, HttpServletRequest request){
        int id= (int) request.getAttribute("id");
        log.info("id==="+id);
        userInfo.setId(id);
        return userInfoService.UpdateUserInfo(userInfo);
    }
    @PostMapping("/UpdatePassword")
    public Result UpdatePassword(@RequestParam String code, @RequestParam String phoneNumber , @RequestParam String NewPassword, HttpServletRequest request){
        // 处理注册逻辑
        // 返回注册结果
        //1、从redis中获取验证码，如果获取到就直接返回
        String code1 = redisTemplate.opsForValue().get(phoneNumber);
        int id= (int) request.getAttribute("id");
        log.info(code);
        Result rs=new Result();
        rs.setCode(500);
        rs.setMsg("验证码错误");
        if(code.equals(code1) && loginService.isIdOk(id,phoneNumber)){
            //验证码验证注册后删除对应值
            redisTemplate.delete(phoneNumber);
            return loginService.UpdatePassword(phoneNumber,NewPassword);
        }else{
            return rs;
        }
    }
}
