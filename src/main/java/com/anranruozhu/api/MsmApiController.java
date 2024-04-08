package com.anranruozhu.api;


import com.anranruozhu.mapper.UserMapper;
import com.anranruozhu.service.SmsService;
import com.anranruozhu.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author anranruozhu
 * @ClassName MsmApiController
 * @Description 短信测试接口
 * @create 2024/4/8 上午10:10
 **/

@RestController
@RequestMapping("/api/msm")
public class MsmApiController {
    @Autowired
    private SmsService msmService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;    //注入redis
    //发送短信验证码
    @ApiOperation(value = "发送短信验证码")
    @GetMapping(value = "/RegisterSend")
    public Boolean code(@RequestParam String phone) {
        //首先先验证手机号是否注册过。
        if(userMapper.findByPhone(phone)==null){
            //1、使用随机生成验证码的工具来生成随机验证码。
            String code = RandomUtil.getFourBitRandom();//生成验证码的随机值
            //2.使用Map来进行code的存储映射，然后使得短发发送方法进行业务。
            Map<String,Object> param = new HashMap<>();
            param.put("code", code);
            //调用方法
            boolean isSend = msmService.send(param,phone);
            if(isSend) {
                //往redis中设置数据：key、value、过期值、过期时间单位  MINUTES代表分钟
                redisTemplate.opsForValue().set(phone, code,5, TimeUnit.MINUTES);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
