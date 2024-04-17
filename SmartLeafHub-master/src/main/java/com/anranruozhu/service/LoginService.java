package com.anranruozhu.service;

import com.anranruozhu.common.Result;

/**
 * @author anranruozhu
 * @ClassName LoginService
 * @Description 登陆业务类
 * @create 2024/4/7 10:28
 **/
public interface LoginService {
    /**
     * 发送短信验证码
     * @param phone 手机号码
     * @param password 用户密码
     * @return Result
     */
    Result login(String phone, String password) ;
    Result UpdatePassword(String phone, String password);
    boolean isIdOk(int id, String phone);
}
