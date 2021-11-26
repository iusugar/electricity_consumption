package com.zust.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
public class MqttSubscriber implements ApplicationRunner {

  @Resource
  private MqttProperties mqttProperties;
  private MqttClient mqttClient;

  @Override
  public void run(ApplicationArguments args) throws Exception {
    this.connect();
  }

  public void connect() {
    try {
      if (mqttClient ==null) {
        mqttClient = new MqttClient(mqttProperties.getHost(),mqttProperties.getClientId(),new MemoryPersistence());
        log.info("创建mqttClient成功");
      }
    } catch (MqttException e) {
      e.printStackTrace();
      log.error("创建mqttClient失败");
    }

    MqttConnectOptions connectOptions = new MqttConnectOptions();
    connectOptions.setUserName(mqttProperties.getUsername());
    connectOptions.setPassword(mqttProperties.getPassword().toCharArray());
    connectOptions.setConnectionTimeout(mqttProperties.getTimeout());
    connectOptions.setKeepAliveInterval(mqttProperties.getKeepalive());
    connectOptions.setCleanSession(true);

    // 设置订阅主题的qos
    String[] topics = mqttProperties.getTopic();
    int[] qos = new int[topics.length];
    for (int i = 0; i < topics.length; i++) {
      qos[i] = 0;
    }

    mqttClient.setCallback(new MyMqttCallback(mqttClient,connectOptions,topics,qos));

    try {
      mqttClient.connect(connectOptions);
      log.info("mqttClient连接成功");
    } catch (MqttException e) {
      log.error("mqttClient连接失败" + e.getMessage());
      e.printStackTrace();
    }

    try {
      mqttClient.subscribe(topics,qos);
      log.info("mqttClient订阅成功");
    } catch (MqttException e) {
      log.error(e.toString());
      e.printStackTrace();
    }

  }
}
