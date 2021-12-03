package com.zust.mqtt;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class HandleMessageThread extends Thread{

	MqttClient mqttClient;
	Integer id;
	String deviceId;
	MqttConnectOptions options;
	String[] topics;
	int[] qos;
	public HandleMessageThread(MqttClient mqttClient, Integer id, String deviceId, MqttConnectOptions options, String[] topics, int[] qos){
		this.mqttClient = mqttClient;
		this.id = id;
		this.deviceId = deviceId;
		this.options = options;
		this.qos = qos;
	}

	@Override
	public void run() {
//		mqttClient.setCallback(new MyMqttCallback(mqttClient,options,topics,qos,id,deviceId));
	}
}
