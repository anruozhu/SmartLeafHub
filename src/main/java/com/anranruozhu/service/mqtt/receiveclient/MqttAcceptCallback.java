package com.anranruozhu.service.mqtt.receiveclient;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Component
@Data
public class MqttAcceptCallback implements MqttCallbackExtended {

    private static final Logger logger = LoggerFactory.getLogger(MqttAcceptCallback.class);

    private String s;

    @Autowired
    private MqttAcceptClient mqttAcceptClient;

    /**
     * 客户端断开后触发
     *
     * @param throwable
     */
    @Override
    public void connectionLost(Throwable throwable) {
        logger.info("连接断开，可以做重连");
        if (MqttAcceptClient.client == null || !MqttAcceptClient.client.isConnected()) {
            logger.info("emqx重新连接....................................................");
   
        }
    }

    /**
     * 客户端收到消息触发
     *
     * @param topic       主题
     * @param mqttMessage 消息
     */
    @Override
    public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
        s =  new String(mqttMessage.getPayload());
        logger.info("接收消息主题 : " + topic);
        logger.info("接收消息Qos : " + mqttMessage.getQos());
        logger.info("接收的消息内容 : " + s);
        Date date3 = DateUtil.date(System.currentTimeMillis());
//        //时间
//        String formatDateTime = DateUtil.formatDateTime(date3);
//        //数据
//        JSONObject jsonObject = JSONUtil.parseObj(s);
//        logger.info(formatDateTime);
//        logger.info(jsonObject.toString());
//        //存入数据
//        Db.use().execute("insert into cqzr1(ctime,a_temp,a_hum,moisture) values (?, ?, ?, ?)", formatDateTime, jsonObject.getStr("temp"), jsonObject.getStr("hum"),jsonObject.getStr("moisture"));
////        int i = 1/0;
    }

    /**
     * 发布消息成功
     *
     * @param token token
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        String[] topics = token.getTopics();
        for (String topic : topics) {
            logger.info("向主题：" + topic + "发送消息成功！");
        }
        try {
            MqttMessage message = token.getMessage();
            byte[] payload = message.getPayload();
            String s = new String(payload, "UTF-8");
            logger.info("消息的内容是：" + s);
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 连接emq服务器后触发
     *
     * @param b
     * @param s
     */
    @Override
    public void connectComplete(boolean b, String s) {
        logger.info("--------------------ClientId:"
                + MqttAcceptClient.client.getClientId() + "客户端连接成功！--------------------");
        // 以/#结尾表示订阅所有以test开头的主题
        // 订阅所有机构主题
//        mqttAcceptClient.subscribe("testtopic/#", 0);
        mqttAcceptClient.subscribe("wwj", 0);
    }
}
