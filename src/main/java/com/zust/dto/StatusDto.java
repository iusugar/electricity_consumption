package com.zust.dto;

import lombok.Data;

import java.util.Date;

@Data
public class StatusDto {
	private Integer id;
	private Integer devId;
	private Date lastUseTime;
	private Object currentState;
	private Integer locaId;
	private Integer gwId;
	// 增加24位设备ID
	private String deviceId;
	// 房间号
	private String roomNum;
	private String location;
}
