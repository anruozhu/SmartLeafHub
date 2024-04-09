package com.anranruozhu.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SersorDataMapper {
    void addData(float airTemperature,float lightIntensity);
    float ShowLastTemperature();
    float ShowLastlightIntensity();
}