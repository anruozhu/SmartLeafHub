package com.anranruozhu.api;

import com.anranruozhu.common.Result;
import com.anranruozhu.config.AutoConfig;
import com.anranruozhu.entity.AutoStatus;
import com.anranruozhu.entity.Weather;
import com.anranruozhu.mapper.AutoStatusMapper;
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
    private AutoStatusMapper autoStatusMapper;
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private DataAccess dataAccess;
    @Autowired
    private AutoConfig autoConfig;
    @Autowired
    private WeatherService weatherService;
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
    public String getPumpAutoStatus() {
        AutoStatus data=autoStatusMapper.getStatus();
        System.out.println(data.getPumpStatus());
        if(data.getPumpStatus()==1){
            return "开";
        }else{
            return "关";
        }
    }
    @GetMapping("/getLightAutoStatus")
    public String getLightAutoStatus() {
        AutoStatus data=autoStatusMapper.getStatus();
        System.out.println(data.getLightStatus());
        if(data.getLightStatus()==1){
            return "开";
        }else{
            return "关";
        }
    }
    @GetMapping("/getFenAutoStatus")
    public String getFenAutoStatus() {
        AutoStatus data=autoStatusMapper.getStatus();
        System.out.println(data.getFenStatus());
        if(data.getFenStatus()==1){
            return "开";
        }else{
            return "关";
        }
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
 }
