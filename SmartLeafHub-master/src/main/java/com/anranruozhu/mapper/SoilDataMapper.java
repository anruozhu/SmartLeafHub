package com.anranruozhu.mapper;

import com.anranruozhu.entity.SoilData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SoilDataMapper {
        // 添加数据
        void addData(Float soilHumidity);
        //获取最新得到数据
        Float ShowLast();
        //获取历史数据
        List<SoilData> ShowAll();
        List<SoilData> ShowWeekData();
}
