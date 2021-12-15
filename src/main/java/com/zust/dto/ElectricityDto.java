package com.zust.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ElectricityDto {

	private Integer id;
	private Integer devId;
	private Date createTime;
	private float consumption;

	// 24位设备号
	private String deviceId;
}
