package com.anranruozhu.entry;

import lombok.Data;

import java.sql.Time;
import java.util.Date;

@Data
public class SensorData {
    private Long id;
    private int temp;
    private int wet;
    private String status;
    private Date date_reported;
    private Time time_reported;
    // Getters and setters
    public SensorData(int tmp,int wet,String status){
        this.temp=tmp;
        this.wet=wet;
        this.status=status;
    }
}
