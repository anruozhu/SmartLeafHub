package com.anranruozhu.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author anranruozhu
 * @ClassName SersorData
 * @Description 光照温度数据类
 * @create 2024/4/9 上午11:22
 **/
@Data
public class SersorData {
    private int id;
    private float lightINtensity;
    private float airTemperature;
}
