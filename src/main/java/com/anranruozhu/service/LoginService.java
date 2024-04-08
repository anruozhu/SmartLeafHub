package com.anranruozhu.service;

import cn.hutool.json.JSONObject;
import com.anranruozhu.common.Result;
import com.anranruozhu.entry.User;
import com.anranruozhu.mapper.UserMapper;
import com.anranruozhu.utils.JwtUtil;
import com.anranruozhu.utils.MD5;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LoginService {
    @Autowired
    private UserMapper userMapper;
    Result result=new Result();
    public Result login(String phone, String password) {

        String encodedPwd = MD5.md5(password, phone);
        User user= userMapper.login(phone, encodedPwd);
        JSONObject json = new JSONObject();
        Result result=new Result();
        if (user!=null) {
            log.info("登录成功！生成token！");
            String token = JwtUtil.createToken(user);
            result.setCode(200);
            result.setMsg("登录成功！");
            json =  new JSONObject()
                    .set("token", token)
                    .set("user", new JSONObject()
                            .set("id", user.getId())
                            .set("phone",user.getPhoneNumber()
                            ));
            result.setData(json);
            return result;
        }else{
            log.info("登录失败！");
            result.setCode(400);
            if (phone == null || phone.length() == 0) {
                result.setMsg("电话号码不能为空");
            }else{
                result.setMsg("账号或密码错误！");
            }
            return result;
        }
    }
}
