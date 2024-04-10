package com.anranruozhu.api;

import cn.hutool.json.JSONObject;
import com.anranruozhu.service.mqtt.sendclient.MqttSendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/secure")
@CrossOrigin(origins = "*")
public class MqttController {
    @Autowired
    private MqttSendClient client1;
    @GetMapping("/WindAndPumpctl")
    private Object windctl(@RequestParam String p_c_state,
                           @RequestParam String p_p_state,
                           @RequestParam String f_mode,
                           @RequestParam String f_level
                              )  {
        client1.connect();
        String topic = "ctl-a-1";
        JSONObject data=new JSONObject().set("pump_ctrl_state",p_c_state)
                                        .set("pump_power_state",p_p_state)
                                        .set("fan_mode",f_mode)
                                        .set("fan_level",f_level);
        client1.publish(topic, String.valueOf(data));
        client1.disconnect();
        client1.close();
        JSONObject json = new JSONObject();
        json.putOnce("topic", topic);
        json.putOnce("instruction", data);
        return json;
    }
    @GetMapping("/watetctl/{ctl}")
    private Object watetctl(@PathVariable String ctl) {
        client1.connect();
        String topic = "ctl-b-1";
        client1.publish(topic, ctl);
        JSONObject json = new JSONObject();
        json.putOnce("topic", topic);
        json.putOnce("status", ctl);
        return json;
    }
}
