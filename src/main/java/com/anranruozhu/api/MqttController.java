package com.anranruozhu.api;

import cn.hutool.json.JSONObject;
import com.anranruozhu.common.Result;
import com.anranruozhu.service.mqtt.sendclient.MqttSendClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secure")
public class MqttController {
    @Autowired
    private MqttSendClient client1;
    @GetMapping("/windctl/{ctl}")
    private Object windctl(@PathVariable String ctl) {
        client1.connect();
        String topic = "topicC";
        client1.publish(topic, ctl);
        JSONObject json = new JSONObject();
        json.putOnce("topic", topic);
        json.putOnce("status", ctl);
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
