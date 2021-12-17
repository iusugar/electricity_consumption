package com.zust.dto;

import lombok.Data;

import java.util.Date;

@Data
public class HistoricalDto {

	private Integer id;
	private Integer devId;
	private Date createTime;
	private Object status;

	// 设备对象属性
	private String deviceId;
}
