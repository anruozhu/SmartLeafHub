package com.anranruozhu.mapper;

import com.anranruozhu.entity.AlertData;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AlertDataMapper {
    void AlertNew(int alertType,
                  String alertMessage,float alertData);
    AlertData getAlertData(int alertType);
}
