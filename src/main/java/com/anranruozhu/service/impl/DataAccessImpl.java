package com.anranruozhu.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.anranruozhu.common.Result;
import com.anranruozhu.entity.DeviceState;
import com.anranruozhu.entity.LightInstrustions;
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

    @Override
    public DeviceState getDeviceState() {
        DeviceState res = new DeviceState();
        try {
            res = deviceStateMapper.ShowLast();
        }catch (Exception e){
            log.error("error: " + e.getMessage());
            throw new RuntimeException("获取设备状态失败");
        }
        return res;
    }

    @Override
    public LightInstrustions getLightState() {
        LightInstrustions res = new LightInstrustions();
        try {
            res = lightInstrustionsMapper.ShowLast();
        } catch (Exception e){
            log.error("error: " + e.getMessage());
            throw new RuntimeException("获取灯光状态失败");
        }
        return res;
    }

    public Result getDeviceAndLightState() {
        Result res = new Result();
        DeviceState device = getDeviceState();
        LightInstrustions light = getLightState();
        JSONObject data = new JSONObject();
        data.set("pumpCtrlState", device.getPumpCtrlState())
                .set("pumpPowerState", device.getPumpPowerState())
                .set("fanMode", device.getFanMode())
                .set("fanLevel", device.getFanLevel())
                .set("lightMode", light.getLightMode())
                .set("light_level", light.getLight_level());
        res.setData(data);
        res.setCode(200);
        res.setMsg("获取成功");
        return res;
    }
}
