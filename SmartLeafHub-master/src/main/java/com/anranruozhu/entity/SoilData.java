package com.anranruozhu.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author anranruozhu
 * @ClassName SoilData
 * @Description 土壤数据
 * @create 2024/4/9 上午11:26
 **/
@Data
public class SoilData {
    private float soilHumidity;
    private Timestamp readingTime;
}
