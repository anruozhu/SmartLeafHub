package com.anranruozhu.service;

import com.anranruozhu.common.Result;
import com.anranruozhu.entity.soilData;

import java.util.List;

public interface MonitorService {

    /**
     * 获取所有的土壤湿度数据
     */
    List<soilData> getHumidity();

    /**
     * 获取最新的土壤湿度数据
     */
    Result getNewHumidity();
    /**
     * 获取最新的温度数据
     */
    Result getNewTemperature();
    /**
     * 获取最新的光照数据
     */
    Result getNewLightIntensity();

}
