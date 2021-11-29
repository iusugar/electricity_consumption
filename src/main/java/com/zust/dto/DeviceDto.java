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

  /**
  * 设备ID
  */
  private String deviceId;
  /**
  * 用途，用来说明负载
  */
  private String usageDesc;

	/**
	 * 空间位置
	 */
  private String buildNum;
  private String roomNum;
  private String location;

  /**
  * 额定电压
  */
  private float ratedVoltage;
  /**
  * 额定电流
  */
  private float ratedCurrent;
  /**
  * 额定功率
  */
  private float ratedPower;

	private Date createTime;

  private List<String> checkedFunctions;

	private int voltageMonitor;

	private int currentMonitor;

	private int powerMonitor;

	private int remoteControl;

	private int elecConsum;

	private int infraredFunc;

}