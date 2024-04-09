package com.anranruozhu.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.anranruozhu.mapper.SersorDataMapper;
import com.anranruozhu.service.DataAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author anranruozhu
 * @ClassName DataAccessImpl
 * @Description 对订阅数据的存储保存
 * @create 2024/4/9 上午11:42
 **/
@Component
public class DataAccessImpl implements DataAccess {
    @Autowired
    private SersorDataMapper sersorDataMapper;

    @Override
    public void SaveSersor(String message) {
        //数据
        JSONObject jsonObject = JSONUtil.parseObj(message);
        float lightINtensity = jsonObject.getFloat("light_intensity");
        float airTemperature = jsonObject.getFloat("air_temperature");
        sersorDataMapper.addData(airTemperature,lightINtensity);
    }

    @Override
    public void SaveSoil(String message) {

    }
}
