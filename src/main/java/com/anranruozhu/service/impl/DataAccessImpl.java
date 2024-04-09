package com.anranruozhu.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.anranruozhu.mapper.SersorDataMapper;
import com.anranruozhu.mapper.soilDataMapper;
import com.anranruozhu.service.DataAccess;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName DataAccessImpl
 * @Description 对订阅数据的存储保存
 * @create 2024/4/9 上午11:42
 **/
@Component
@Slf4j
public class DataAccessImpl implements DataAccess {
    @Autowired
    private SersorDataMapper sersorDataMapper;
    @Autowired
    private soilDataMapper soilDataMapper;

    @Override
    public void SaveSersor(String message) {
        //数据
        JSONObject jsonObject = JSONUtil.parseObj(message);
        float lightINtensity = jsonObject.getFloat("light_intensity");
        float airTemperature = jsonObject.getFloat("air_temperature");
        try {
            sersorDataMapper.addData(airTemperature,lightINtensity);
        }catch (Exception e){
            log.error("error: " + e.getMessage());
            throw new RuntimeException("光照气温保存失败");
        }
    }

    @Override
    public void SaveSoil(String message) {
        JSONObject jsonObject = JSONUtil.parseObj(message);
        float soil_humidity = jsonObject.getFloat("soil_humidity");
        try {
            soilDataMapper.addData(soil_humidity);
        }catch (Exception e){
            log.error("error: " + e.getMessage());
            throw new RuntimeException("土壤数据保存失败");
        }
    }
}
