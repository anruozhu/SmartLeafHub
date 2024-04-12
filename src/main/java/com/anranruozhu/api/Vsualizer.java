package com.anranruozhu.api;

import com.anranruozhu.common.Result;
import com.anranruozhu.service.DataAccess;
import com.anranruozhu.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author anranruozhu
 * @ClassName Vsualizer
 * @Description 大屏端的展示
 * @create 2024/4/11 下午4:17
 **/
@RestController
@RequestMapping("/api/vsualizer")
public class Vsualizer
{
    @Autowired
    private MonitorService monitorService;
    @Autowired
    private DataAccess dataAccess;
    //获取实时的温度
    @GetMapping("/temperature")
    public Result getTemperature(){
        return monitorService.getNewTemperature();
    }
    //获取实时的湿度
    @GetMapping("/humidity")
    public Result getHumidity(){
        return monitorService.getNewHumidity();
    }
    //获取实时的光照强度
    @GetMapping("/light-intensity")
    public Result getLightIntensity() {
        return monitorService.getNewLightIntensity();
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
}
