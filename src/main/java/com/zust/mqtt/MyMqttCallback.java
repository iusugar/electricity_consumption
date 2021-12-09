package com.zust.mqtt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zust.dao.DeviceStatusDao;
import com.zust.dao.HistoricalStatusDao;
import com.zust.entity.Device;
import com.zust.entity.DeviceStatus;
import com.zust.entity.ElectricityData;
import com.zust.entity.HistoricalStatus;
import com.zust.service.DeviceService;
import com.zust.service.DeviceStatusService;
import com.zust.service.ElectricityDataService;
import com.zust.service.HistoricalStatusService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.nio.channels.NonWritableChannelException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class MyMqttCallback implements MqttCallbackExtended {

  private static final String TOPIC_1 = "/boxcloud/KBOXTEST0002/custom/data/point";
  private static final String TOPIC_2 = "/boxcloud/KBOXTEST0002/device/status";

  public MqttClient mqttClient;
  public MqttConnectOptions connectOptions;
  public String[] topics;
  public int[] qos;

  private static Timer dataTimer;
  private static int saveDataFlag = 0;
  int messageCount = 0;

  @Resource
  private ElectricityDataService electricityDataService;
  @Resource
  private DeviceService deviceService;
	@Resource
	private DeviceStatusService statusService;
	@Resource
	private HistoricalStatusService historicalStatusService;

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
	  dataTimer = new Timer();
  }

  @Override
  public void connectComplete(boolean b, String s) {
    log.info("完成连接");
	  dataTimer.schedule(new TimerTask() {
      @Override
      public void run() {
        saveDataFlag = 1;
      }
    },0,5 * 60 * 1000);
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
        if (saveDataFlag == 1) {
          handleDataMessage(mqttMessage);
        }
        break;
      }
      case TOPIC_2: {
				handleStateMessage(mqttMessage);
				break;
      }
    }
    messageCount++;
//    System.out.println("消息数量:" + messageCount);
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
    System.out.println("delivery");
  }

  public void handleDataMessage(MqttMessage message) throws JsonProcessingException {
		// 转json
    ObjectMapper mapper = new ObjectMapper();
    Map<String,String> result = mapper.readValue(message.toString(), new TypeReference<Map<String,String>>(){});
    Device device = myMqttCallback.deviceService.queryByDeviceId(result.get("deviceID"));
    ElectricityData ed = new ElectricityData();
		if (device != null) {
			ed.setDevId(device.getId());
			ed.setCreateTime(new Date());
			ed.setInsVoltage(result.get("U"));
			ed.setInsCurrent(result.get("I"));
			ed.setInsPower(result.get("P"));
			ed.setConsumption(result.get("Ph"));
			myMqttCallback.electricityDataService.insert(ed);
			saveDataFlag = 0;
		}
//    System.out.println(result);
  }

	public void handleStateMessage(MqttMessage stateMsg) throws JsonProcessingException {
//        \{([^{}]*)\}
		String stateString = stateMsg.toString();
		Pattern pattern = Pattern.compile("\\{([^{}]*)\\}");
		Matcher matcher = pattern.matcher(stateString);
		List<String> stateList = new ArrayList<>();
		while (matcher.find()) {
			stateList.add(matcher.group());
		}
		ObjectMapper mapper = new ObjectMapper();
    for (String state : stateList) {
	    Map<String,String> result = mapper.readValue(state, new TypeReference<Map<String, String>>(){});
			Device device = null;
			if (result != null) {
				device = myMqttCallback.deviceService.queryByDeviceId(result.get("id"));
			} else {
				continue;
			}
			if (device != null) {
				DeviceStatus deviceStatus = myMqttCallback.statusService.queryByDevId(device.getId());
				boolean newStatus = Integer.parseInt(result.get("status")) != 0;
        if (newStatus != (Boolean) deviceStatus.getCurrentState()) {
	        deviceStatus.setLastUseTime(new Date());
	        deviceStatus.setCurrentState(result.get("status"));
	        myMqttCallback.statusService.update(deviceStatus);
        }
				HistoricalStatus hs = myMqttCallback.historicalStatusService.getByDevId(device.getId());
				if (hs != null) {
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
          String hsDate = dateFormat.format(hs.getCreateTime());
					String today = dateFormat.format(new Date());
					// 最新的一条数据不是今天
//					if (!hsDate.equals(today)) {
//						// 直接添加
//            System.out.println(true);
//					} else if ((Boolean) newStatus == hs.getStatus()){
//            System.out.println(false);
//					}
					if (!hsDate.equals(today) || newStatus != (Boolean) hs.getStatus()) {
						hs.setCreateTime(new Date());
						hs.setStatus(result.get("status"));
						hs.setDevId(device.getId());
						myMqttCallback.historicalStatusService.insert(hs);
					}
				} else {
					HistoricalStatus newHs = new HistoricalStatus();
					newHs.setCreateTime(new Date());
					newHs.setStatus(result.get("status"));
					newHs.setDevId(device.getId());
					myMqttCallback.historicalStatusService.insert(newHs);
				}
			}
    }
	}
}
