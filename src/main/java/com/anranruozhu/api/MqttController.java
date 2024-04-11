package com.anranruozhu.api;

import cn.hutool.json.JSONObject;
import com.anranruozhu.common.Result;
import com.anranruozhu.service.DataAccess;
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
    private DataAccess dataAccess;
    @GetMapping("/WindAndPumpctl")
    public Object windctl(@RequestParam int p_c_state,
                           @RequestParam int p_p_state,
                           @RequestParam int f_mode,
                           @RequestParam int f_level
                              )  {
        client1.connect();
        String topic = "ctl-a-1";
        JSONObject data=new JSONObject().set("pump_ctrl_state",p_c_state)
                                        .set("pump_power_state",p_p_state)
                                        .set("fan_mode",f_mode)
                                        .set("fan_level",f_level);
        client1.publish(topic, String.valueOf(data));
        dataAccess.SaveDeviceState(p_c_state, p_p_state, f_mode, f_level);
        client1.disconnect();
        client1.close();
        JSONObject json = new JSONObject();
        json.putOnce("topic", topic);
        json.putOnce("instruction", data);
        return json;
    }
    @GetMapping("/lightctl")
    public Object lightctl(@RequestParam int light_mode,
                            @RequestParam int light_level
    ) {
        client1.connect();
        String topic = "ctl-b-1";
        JSONObject data = new JSONObject()
                .set("light_mode", light_mode)
                .set("light_level", light_level);
        client1.publish(topic, String.valueOf(data));
        dataAccess.SaveInstructions(light_mode,light_level);
        JSONObject json = new JSONObject();
        json.putOnce("topic", topic);
        json.putOnce("instruction", data);
        return json;
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
