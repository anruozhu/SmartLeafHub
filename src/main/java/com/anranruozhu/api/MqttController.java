package com.anranruozhu.api;

import cn.hutool.json.JSONObject;
import com.anranruozhu.common.Result;
import com.anranruozhu.service.mqtt.sendclient.MqttSendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MqttController {
    @Autowired
    private MqttSendClient client1;
    @GetMapping("/windctl/{ctl}")
    private Object windctl(@PathVariable String ctl) {
        String topic = "topicC";
        client1.publish(topic, ctl);
        JSONObject json = new JSONObject();
        json.putOnce("topic", topic);
        json.putOnce("status", ctl);
        return json;
    }

    @GetMapping("/watetctl/{ctl}")
    private Object watetctl(@PathVariable String ctl) {
        String topic = "topicD";
        client1.publish(topic, ctl);
        JSONObject json = new JSONObject();
        json.putOnce("topic", topic);
        json.putOnce("status", ctl);
        return json;
    }
}
