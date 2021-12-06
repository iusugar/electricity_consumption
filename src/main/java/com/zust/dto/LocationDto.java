package com.zust.dto;

import lombok.Data;

/**
 * 具体位置映射设备传输对象
 * @author iusugar
 * @since 2021-11-24 12:34:56
 */
@Data
public class LocationDto {
// 实体属性
	private Integer id;
	private Integer roomId;
	private String position;
	private String description;
// 增加属性
	/*房间名*/
	private String roomName;
	/*设备ID*/
	private Integer devId;


}
