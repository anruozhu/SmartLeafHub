package com.anranruozhu.mapper;

import com.anranruozhu.entity.LightIntensityData;
import com.anranruozhu.entity.TemperatureData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SersorDataMapper {
    void addData(float airTemperature,float lightIntensity);
    float ShowLastTemperature();
    float ShowLastlightIntensity();
    List<TemperatureData> ShowAllTemperature();
    List<LightIntensityData> ShowAlllightIntensity();
}