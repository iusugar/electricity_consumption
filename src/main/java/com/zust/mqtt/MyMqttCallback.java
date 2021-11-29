package com.zust.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zust.entity.Device;
import com.zust.entity.ElectricityData;
import com.zust.service.DeviceService;
import com.zust.service.ElectricityDataService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@Slf4j
@Component
public class MyMqttCallback implements MqttCallbackExtended {

  private static final String TOPIC_1 = "/boxcloud/KBOXTEST0002/custom/data/point";
  private static final String TOPIC_2 = "/boxcloud/KBOXTEST0002/device/status";

  public MqttClient mqttClient;
  public MqttConnectOptions connectOptions;
  public String[] topics;
  public int[] qos;

  private static Timer timer;
  private static int flag = 0;
  int messageCount = 0;

  @Resource
  private ElectricityDataService electricityDataService;
  @Resource
  private DeviceService deviceService;

  private static MyMqttCallback myMqttCallback;

  public MyMqttCallback() {}

  public MyMqttCallback(MqttClient client, MqttConnectOptions options, String[] topics, int[] qos) {
    this.mqttClient = client;
    this.connectOptions = options;
    this.topics = topics;
    this.qos = qos;
  }

  @PostConstruct
  public void init() {
    log.info("初始化");
    myMqttCallback = this;
    myMqttCallback.electricityDataService = this.electricityDataService;
    myMqttCallback.deviceService = this.deviceService;
    timer = new Timer();
  }

  @Override
  public void connectComplete(boolean b, String s) {
    log.info("完成连接");
    timer.schedule(new TimerTask() {
      @Override
      public void run() {
        flag = 1;
      }
    },0,2 * 60 * 1000);
  }

  @SneakyThrows
  @Override
  public void connectionLost(Throwable throwable) {
    log.error(String.valueOf(throwable));
    log.error("断开连接connection lost");
    if (mqttClient != null && !mqttClient.isConnected()) {
      mqttClient.reconnect();
    } else {
      assert mqttClient != null;
      mqttClient.connect(connectOptions);
    }
  }

  @Override
  public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
    switch (s) {
      case TOPIC_1: {
        if (flag == 1) {
          handleMqttMessage(mqttMessage);
        }
        break;
      }
      case TOPIC_2:
        System.out.println(2);
        break;
    }
    messageCount++;
//    System.out.println("消息数量:" + messageCount);
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    System.out.println("delivery");
  }

  public void handleMqttMessage(MqttMessage message) throws JsonProcessingException {
		// 转json
    ObjectMapper mapper = new ObjectMapper();
    Map<String,String> result = mapper.readValue(message.toString(), new TypeReference<Map<String,String>>(){});
    Device device = myMqttCallback.deviceService.queryByDeviceId(result.get("deviceID"));
    ElectricityData ed = new ElectricityData();
    ed.setDevId(device.getId());
    ed.setCreateTime(new Date());
    ed.setInsVoltage(result.get("U"));
    ed.setInsCurrent(result.get("I"));
    ed.setInsPower(result.get("P"));
		ed.setConsumption(result.get("Ph"));
    myMqttCallback.electricityDataService.insert(ed);
    flag = 0;
//    System.out.println(result);
  }
}
