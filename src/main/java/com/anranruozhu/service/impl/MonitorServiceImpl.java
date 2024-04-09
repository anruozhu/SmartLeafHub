package com.anranruozhu.service.impl;

import cn.hutool.json.JSONObject;
import com.anranruozhu.common.Result;
import com.anranruozhu.entity.soilData;
import com.anranruozhu.mapper.SersorDataMapper;
import com.anranruozhu.mapper.soilDataMapper;
import com.anranruozhu.service.MonitorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author anranruozhu
 * @ClassName MonitorServiceImpl
 * @Description 数据监控服务的实现类
 * @create 2024/4/9 下午8:35
 **/
@Slf4j
@Component
public class MonitorServiceImpl implements MonitorService {
    @Autowired
    private soilDataMapper soildataMapper;
    @Autowired
    private SersorDataMapper sersorDataMapper;

    @Override
    public List<soilData> getHumidity() {
        return soildataMapper.ShowAll();
    }
    @Override
    public Result getNewHumidity() {
        Result rs=new Result();
        try {
           float Humidity = soildataMapper.ShowLast();
           rs.setCode(200);
            JSONObject data=new JSONObject().set("humidity",Humidity);
           rs.setData(data);
           rs.setMsg("获取成功");
        }catch (Exception e){
            log.error(e.getMessage());
            rs.setCode(500);
            rs.setMsg("获取失败");
        }
        return rs;
    }

    @Override
    public Result getNewTemperature() {
        Result rs=new Result();
        try {
            float Temperature = sersorDataMapper.ShowLastTemperature();
            rs.setCode(200);
            JSONObject data=new JSONObject().set("temperature",Temperature);
            rs.setData(data);
            rs.setMsg("获取成功");
        }catch (Exception e){
            log.error(e.getMessage());
            rs.setCode(500);
            rs.setMsg("获取失败");
        }
        return rs;
    }
    @Override
    public Result getNewLightIntensity() {
        Result rs=new Result();
        try {
            float LightIntensity = sersorDataMapper.ShowLastlightIntensity();
            rs.setCode(200);
            JSONObject data=new JSONObject().set("lightIntensity",LightIntensity);
            rs.setData(data);
            rs.setMsg("获取成功");
        }catch (Exception e){
            log.error(e.getMessage());
            rs.setCode(500);
            rs.setMsg("获取失败");
        }
        return rs;
    }
}
