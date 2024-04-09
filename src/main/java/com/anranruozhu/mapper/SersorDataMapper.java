package com.anranruozhu.mapper;

import com.anranruozhu.entity.SersorData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SersorDataMapper {
    void addData(float airTemperature,float lightIntensity);
}