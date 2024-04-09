package com.anranruozhu.api;

import com.anranruozhu.common.Result;
import com.anranruozhu.entity.UserInfo;
import com.anranruozhu.mapper.UserMapper;
import com.anranruozhu.service.LoginService;
import com.anranruozhu.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author anranruozhu
 * @ClassName MonitorApi
 * @Description 监控业务的接口
 * @create 2024/4/7 11:28
 **/
@RestController
@RequestMapping("/secure")
@Slf4j
public class MonitorApi {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @PostMapping("/SetUserInfo")
    public Result SetUserInfo(@RequestBody UserInfo userInfo,HttpServletRequest request){
        int id= (int) request.getAttribute("id");
        log.info("id==="+id);
        userInfo.setId(id);
        return userInfoService.UpdateUserInfo(userInfo);
    }
    @PostMapping("/UpdatePassword")
    public Result UpdatePassword(@RequestParam String code1,String phone ,String NewPassword){
        // 处理注册逻辑
        // 返回注册结果
        //1、从redis中获取验证码，如果获取到就直接返回
        String code = redisTemplate.opsForValue().get(phone);
        log.info(code);
        Result rs=new Result();
        rs.setCode(500);
        rs.setMsg("验证码错误");
        if(code1.equals(code)){
            //验证码验证注册后删除对应值
            redisTemplate.delete(phone);
            return loginService.UpdatePassword(phone,NewPassword);
        }else{
            return rs;
        }
    }
    @GetMapping("/go")
    public String go(HttpServletRequest request){
        int id= (int) request.getAttribute("id");
        return "go"+id;
    }

    @RequestMapping("/getUserInfo")
    public String login() {
        return "yes";
    }

}
