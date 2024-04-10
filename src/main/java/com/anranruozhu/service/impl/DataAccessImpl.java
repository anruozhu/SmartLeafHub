package com.anranruozhu.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.anranruozhu.mapper.*;
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
    private SoilDataMapper soilDataMapper;
    @Autowired
    private TemperstureDataMapper temperstureDataMapper;
    @Autowired
    private LightDataMapper lightDataMapper;

    @Autowired
    private DeviceStateMapper deviceStateMapper;
    @Autowired
    private LightInstrustionsMapper lightInstrustionsMapper;
    @Override
    public void SaveSersor(String message) {
        //数据
        JSONObject jsonObject = JSONUtil.parseObj(message);
        float soilHumidity = jsonObject.getFloat("soil_humidity");
        float airTemperature = jsonObject.getFloat("air_temperature");
        try {
            soilDataMapper.addData(soilHumidity);
            temperstureDataMapper.addData(airTemperature);
        }catch (Exception e){
            log.error("error: " + e.getMessage());
            throw new RuntimeException("光照气温保存失败");
        }
        log.info("土壤湿度为：{}", soilHumidity);
        log.info("气温为：{}", airTemperature);
    }

    @Override
    public void SaveSoil(String message) {
        JSONObject jsonObject = JSONUtil.parseObj(message);
        float lightIntensity = jsonObject.getFloat("light_intensity");
        try {
            lightDataMapper.addData(lightIntensity);
        }catch (Exception e){
            log.error("error: " + e.getMessage());
            throw new RuntimeException("土光照强度保存失败");
        }
        log.info("光照强度为：{}", lightIntensity);
    }

    @Override
    public void SaveDeviceState(int pumpCtrlState, int pumpPowerState, int fanMode, int fanLevel) {
        try {
            deviceStateMapper.addData(pumpCtrlState, pumpPowerState,fanMode,fanLevel);
        }catch (Exception e){
            log.error("error: " + e.getMessage());
            throw new RuntimeException("devicedata 保存失败");
        }
        log.info("devicedata 保存成功");
    }

    @Override
    public void SaveInstructions(int lightMode, int lightLevel) {
        try {
            lightInstrustionsMapper.addData(lightMode,lightLevel);
        } catch (Exception e) {
            log.error("error: " + e.getMessage());
            throw new RuntimeException("lightInstruction 保存失败");
        }
        log.info("lightInstruction 保存成功");
    }
}
