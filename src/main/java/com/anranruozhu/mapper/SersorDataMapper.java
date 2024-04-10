package com.anranruozhu.mapper;

import com.anranruozhu.entity.SersorData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SersorDataMapper {
    void addData(float airTemperature,float lightIntensity);
    float ShowLastTemperature();
    float ShowLastlightIntensity();
    List<SersorData> ShowAllData();
}