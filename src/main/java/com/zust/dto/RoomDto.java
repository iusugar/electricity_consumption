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

	// 用于接收前端tree组件创建房间和位置对象
	private String checkedBuilding;
	private String buildingDesc;
	private String checkedRoom;
	private String roomDesc;
	private String location;
	private String locationDesc;

	// 用于接收房间修改信息
	private String infoRoom;
	private String infoRoomDesc;
}
