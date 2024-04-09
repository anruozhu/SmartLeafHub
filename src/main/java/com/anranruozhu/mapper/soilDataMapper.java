package com.anranruozhu.mapper;

import com.anranruozhu.entity.soilData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface soilDataMapper {

        // 添加土壤数据
        void addData(Float soilHumidity);
        List<soilData> ShowAll();
        Float ShowLast();
}
