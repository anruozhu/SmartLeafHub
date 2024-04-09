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

    //获取实时的温度
    @GetMapping("/temperature")
    public Result getTemperature(){
        return null;
    }
    //获取实时的湿度
    @GetMapping("/humidity")
    public Result getHumidity(){
        return null;
    }
    //获取实时的光照强度
    @GetMapping("/light-intensity")
    public String getLightIntensity() {
        return null;
    }
    //获取实时的风扇状态
    @GetMapping("/fan-status")
    public String getFanStatus(){
        return null;
    }
    //获取实时的水泵状态
    @GetMapping("/water-pump-status")
    public String getWaterPumpStatus(){
        return null;
    }
    //获取实时的led灯状态
    @GetMapping("led-status")
    public String getLEDStatus(){
        return null;
    }
}
