package com.anranruozhu.utils;

import com.anranruozhu.service.mqtt.receiveclient.MqttAcceptClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

    @Autowired
    private MqttAcceptClient mqttAcceptClient1, mqttAcceptClient2;
    /**
     * 订阅mqtt
     *
     * @return
     */

    @Conditional(MqttCondition.class)
    @Bean
    public void createReceiver() {
        mqttAcceptClient1.connect();
        String topic1 = "recieveTopic1";
        mqttAcceptClient1.subscribe(topic1,1);

        String topic2 = "recieveTopic";
        mqttAcceptClient1.subscribe(topic2,1);
    }









}

