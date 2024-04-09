package com.anranruozhu.api;

import cn.hutool.json.JSONObject;
import com.anranruozhu.service.mqtt.sendclient.MqttSendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secure")
public class MqttController {
    @Autowired
    private MqttSendClient client1;
    @GetMapping("/windctl")
    private Object windctl(@RequestParam String mode,@RequestParam String level) {
        client1.connect();
        String topic = "topicC";

        JSONObject data=new JSONObject().set("light_mode",mode)
                                        .set("light_level",level);
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
        String topic = "topicD";
        client1.publish(topic, ctl);
        JSONObject json = new JSONObject();
        json.putOnce("topic", topic);
        json.putOnce("status", ctl);
        return json;
    }
}
