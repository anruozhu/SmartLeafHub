package com.anranruozhu.api;


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
    private RedisTemplate<String, String> redisTemplate;    //注入redis

    //发送短信验证码
    @ApiOperation(value = "发送短信验证码")
    @GetMapping(value = "/send/")
    public Boolean code(@RequestParam String phone) {
        //1、从redis中获取验证码，如果获取到就直接返回
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)) return false;
        //2、如果获取不到，就进行阿里云发送
        code = RandomUtil.getFourBitRandom();//生成验证码的随机值
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

}
