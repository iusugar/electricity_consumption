package com.zust.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * (Device)实体类数据传输对象
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */

@Data
public class DeviceDto {

	private Integer id;
  private String deviceId;
  private String usageDesc;
	// 增加位置属性
  private String buildNum;
  private String roomNum;
  private String location;
	// 增加状态属性
	private Object currentState;

  private float ratedVoltage;
  private float ratedCurrent;
  private float ratedPower;
	private Date createTime;

  private List<String> checkedFunctions;

	private int voltageMonitor;
	private int currentMonitor;
	private int powerMonitor;
	private int remoteControl;
	private int elecConsum;
	private int infraredFunc;

	// 网关
	private String gateway;

}