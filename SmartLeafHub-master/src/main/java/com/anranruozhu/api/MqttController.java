package com.anranruozhu.api;

import cn.hutool.json.JSONObject;
import com.anranruozhu.common.Result;
import com.anranruozhu.config.AutoConfig;
import com.anranruozhu.service.DataAccess;
import com.anranruozhu.service.mqtt.receiveclient.MqttAcceptCallback;
import com.anranruozhu.service.mqtt.sendclient.MqttSendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/secure")
@CrossOrigin(origins = "*")
public class MqttController {
    @Autowired
    private MqttSendClient client1;
    @Autowired
    AutoConfig autoConfig;

    @Autowired
    private DataAccess dataAccess;
    @GetMapping("/Pumpctl")
    public Result pumpctl(@RequestParam int p_c_state){
        Result rs=new Result();
        client1.connect();
        String topic = "ctl-a-1";
        JSONObject data=new JSONObject().set("pump_ctrl_state",p_c_state)
                .set("pump_power_state",p_c_state)
                .set("fan_mode",0)
                .set("fan_level",0);
        client1.publish(topic, String.valueOf(data));
        dataAccess.SaveDeviceState(p_c_state, p_c_state, 0, 0);
        client1.disconnect();
        client1.close();
        rs.setCode(200);
        rs.setMsg("指令已发送");
        rs.setData(new JSONObject().set("pump_ctrl_state",p_c_state));
        return rs;
    }

    @GetMapping("/Windctl")
    public Object windctl(
                           @RequestParam int f_mode,
                           @RequestParam int f_level
                              )  {
        Result rs=new Result();
        client1.connect();
        String topic = "ctl-a-1";
        JSONObject data=new JSONObject().set("pump_ctrl_state",0)
                                        .set("pump_power_state",0)
                                        .set("fan_mode",f_mode)
                                        .set("fan_level",f_level);
        client1.publish(topic, String.valueOf(data));
        dataAccess.SaveDeviceState(0, 0, f_mode, f_level);
        client1.disconnect();
        client1.close();
        rs.setCode(200);
        rs.setMsg("指令已发送");
        rs.setData(new JSONObject().set("fan_mode",f_mode).set("fan_level",f_level));
        return rs;
    }
    @GetMapping("/lightctl")
    public Result lightctl(@RequestParam int light_mode,
                            @RequestParam int light_level
    ) {
        Result rs=new Result();
        client1.connect();
        String topic = "ctl-b-1";
        JSONObject data = new JSONObject()
                .set("light_mode", light_mode)
                .set("light_level", light_level);
        client1.publish(topic, String.valueOf(data));
        dataAccess.SaveInstructions(light_mode,light_level);
        client1.disconnect();
        client1.close();
        rs.setCode(200);
        rs.setMsg("指令已发送");
        rs.setData(new JSONObject().set("light_mode", light_mode)
                .set("light_level", light_level));
        return rs;
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
@GetMapping("/Get_Fen_auto")
    public Result getFenAuto(){
        Result rs=new Result();
        rs.setCode(200);
        if(autoConfig.isFenAuto()){
            autoConfig.setFenAuto(false);
            rs.setData(autoConfig.isFenAuto());
            rs.setMsg("风扇自动化模式已关");
        }else{
            autoConfig.setFenAuto(true);
            rs.setData(autoConfig.isFenAuto());
            rs.setMsg("风扇自动化模式已开");
        }
        return rs;
}
    @GetMapping("/Get_Pump_auto")
    public Result getPumpAuto(){
        Result rs=new Result();
        rs.setCode(200);
        if(autoConfig.isPumpAuto()){
            autoConfig.setPumpAuto(false);
            rs.setData(autoConfig.isPumpAuto());
            rs.setMsg("水泵自动化模式已关");
        }else{
            autoConfig.setPumpAuto(true);
            rs.setData(autoConfig.isPumpAuto());
            rs.setMsg("水泵自动化模式已开");
        }
        return rs;
    }
    @GetMapping("/Get_Light_auto")
    public Result getLightAuto(){
        Result rs=new Result();
        rs.setCode(200);
        if(autoConfig.isLightAuto()){
            autoConfig.setLightAuto(true);
            rs.setData(autoConfig.isLightAuto());
            rs.setMsg("灯光自动化模式已关");
        }else{
            autoConfig.setLightAuto(false);
            rs.setData(autoConfig.isLightAuto());
            rs.setMsg("灯光自动化模式已开");
        }
        return rs;
    }
}
