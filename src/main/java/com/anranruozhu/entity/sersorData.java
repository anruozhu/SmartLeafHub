package com.anranruozhu.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author anranruozhu
 * @ClassName sersorData
 * @Description 存储光照和温度的类
 * @create 2024/4/10 上午11:51
 **/
@Data
public class sersorData {
    private int id;
    private float airTemperature;
    private float lightIntensity;
    private Timestamp readingTime;
}
