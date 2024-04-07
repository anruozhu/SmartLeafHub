package com.anranruozhu.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author anranruozhu
 * @ClassName MonitorApi
 * @Description 监控业务的接口
 * @create 2024/4/7 11:28
 **/
@RestController
public class MonitorApi {
    @GetMapping("/secure/go")
    public String go(){
        return "go";
    }
    @RequestMapping("/secure/getUserInfo")
    public String login() {
        return "yes";
    }
}
