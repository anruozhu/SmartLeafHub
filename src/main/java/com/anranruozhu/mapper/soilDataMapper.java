package com.anranruozhu.mapper;

import com.anranruozhu.entity.soilData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface soilDataMapper {

        // 添加土壤数据
        void addData(soilData sd);
}
