package com.anranruozhu.api;

import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.anranruozhu.common.Result;
import com.anranruozhu.entity.*;
import com.anranruozhu.mapper.AlertDataMapper;
import com.anranruozhu.mapper.AutoStatusMapper;
import com.anranruozhu.mapper.DeviceStateMapper;
import com.anranruozhu.mapper.LightInstrustionsMapper;
import com.anranruozhu.service.DataAccess;
import com.anranruozhu.service.MonitorService;
import com.anranruozhu.service.WeatherService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * @author anranruozhu
 * @ClassName Vsualizer
 * @Description 大屏端的展示
 * @create 2024/4/11 下午4:17
 **/
@RestController
@RequestMapping("/api/vsualizer")
@Data
public class Vsualizer

{
    @Autowired
    private AlertDataMapper alertDataMapper;
    @Autowired
    private AutoStatusMapper autoStatusMapper;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private DataAccess dataAccess;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private DeviceStateMapper deviceStateMapper;
    @Autowired
    private LightInstrustionsMapper lightInstrustionsMapper;
    //获取实时的温度
    @GetMapping("/show_all")
    public Result getTemperature(){
        return monitorService.getAllShow();
    }
    @GetMapping("/temperature-week")
    public Result getTemperatureWeek(){
        return monitorService.getWeekTemperature();
    }
    @GetMapping("/light-intensity-week")
    public Result getLightIntensityWeek(){
        return monitorService.getWeekLightIntensity();
    }
    @GetMapping("/humidity-week")
    public Result getHumidityWeek(){
        return monitorService.getWeekHumidity();
    }
    @GetMapping("alert-info")
    public Result getAlertInfo(@RequestParam int alertType){
        return monitorService.getAlarm(alertType);
    }
    @GetMapping("/get_device_state")
    public Result getDeviceState(){
        return dataAccess.getDeviceAndLightState();
    }
    @GetMapping("/getLightStatus")
    public Result getLightStatus() {
        return dataAccess.getLightInstrustions();
    }
    @GetMapping("/getPumpStatus")
    public Result getPumpStatus() {
        return dataAccess.getPumpStatus();
    }
    @GetMapping("/getFanStatus")
    public Result getFanStatus() {
        return dataAccess.getFanStatus();
    }

    @GetMapping("/getPumpAutoStatus")
    public Result getPumpAutoStatus() {
        Result rs=new Result();
        DeviceState ds=deviceStateMapper.ShowLast();
        AutoStatus data=autoStatusMapper.getStatus();
        JSONObject res=new JSONObject();
        System.out.println(data.getPumpStatus());
        if(ds.getPumpCtrlState()>0) {
            res.set("pump_status","运行中");
        }else{
            res.set("pump_status","已关闭");
        }
        if(data.getPumpStatus()==1){
            res.set("pump_auto","开");
        }else{
           res.set("pump_auto","关");
        }
        rs.setData(res);
        return rs;
    }
    @GetMapping("/getLightAutoStatus")
    public Result getLightAutoStatus() {
        Result rs=new Result();
        LightInstrustions ds=lightInstrustionsMapper.ShowLast();
        AutoStatus data=autoStatusMapper.getStatus();
        JSONObject res=new JSONObject();
        System.out.println(data.getLightStatus());
        if(ds.getLightMode()>0) {
            res.set("light_status","运行中");
        }else{
            res.set("light_status","已关闭");
        }
        if(data.getLightStatus()==1){
            res.set("light_auto","开");
        }else{
            res.set("light_auto","关");
        }
        rs.setData(res);
        return rs;
    }
    @GetMapping("/getFenAutoStatus")
    public Result getFenAutoStatus() {
        Result rs=new Result();
        DeviceState ds=deviceStateMapper.ShowLast();
        AutoStatus data=autoStatusMapper.getStatus();
        JSONObject res=new JSONObject();
        System.out.println(ds.getFanMode());
        if(ds.getFanMode()>0) {
            res.set("fen_status","运行中");
        }else{
            res.set("fen_status","已关闭");
        }
        if(data.getFenStatus()==1){
            res.set("fen_auto","开");
        }else{
            res.set("fen_auto","关");
        }
        rs.setData(res);
        return rs;
    }
    @GetMapping("/weather")
    public Result  getWeather() throws IOException {
        Result rs=new Result();
        //获取天气数据
        List<Weather> weathers = weatherService.getWeather("101040700");
        rs.setCode(200);
        rs.setMsg("查询成功");
        rs.setData(weathers);
        return rs;
    }
    @GetMapping("/getAutoStatus")
    public Result getAutoStatus() {
        Result rs =new Result();
        try{
           List<AlertData> ad=alertDataMapper.getAlertAll();
           rs.setCode(200);
           rs.setMsg("查询成功");
           rs.setData(ad);
        }catch (Exception e){
            rs.setCode(500);
            rs.setMsg("查询失败");
            e.printStackTrace();
        }
        return rs;
    }
 }
