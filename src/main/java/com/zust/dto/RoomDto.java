package com.zust.dto;

import lombok.Data;

@Data
public class RoomDto {
// 实体属性
	private Integer id;

	private String name;

	private String description;

	private Integer pId;

	/*location表属性*/
	private String position;

	/*device表主键ID*/
	private Integer devId;
}
