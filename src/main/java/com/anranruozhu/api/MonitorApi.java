package com.anranruozhu.api;

import com.anranruozhu.common.Result;
import com.anranruozhu.entity.UserInfo;
import com.anranruozhu.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class MonitorApi {

    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("/SetUserInfo")
    public Result SetUserInfo(@RequestBody UserInfo userInfo){
        return userInfoService.UpdateUserInfo(userInfo);
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
