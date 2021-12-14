package com.zust.controller;

import com.zust.dto.LocationDto;
import com.zust.dto.RoomDto;
import com.zust.entity.Location;
import com.zust.entity.Room;
import com.zust.service.LocationService;
import com.zust.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * (Room)表控制层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@RestController
@RequestMapping("room")
public class RoomController {
  /**
   * 服务对象
   */
  @Resource
  private RoomService roomService;
	@Resource
	private LocationService locationService;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public Room selectOne(Integer id) {
      return this.roomService.queryById(id);
  }

  @GetMapping("selectAllRoom")
  public List<Room> selectAllRoom() {
      return roomService.queryAllRoom();
  }

	// 对应transfer添加的控制类 未用
  @PostMapping("add")
  public String addNewRoom(@RequestBody ArrayList<String> roomArray) {
		if (Objects.equals(roomService.insert(roomArray), "success")) {
			return "success";
    }
      return "failed";
  }

	// 添加一个新的房间位置
	@PostMapping("addNew")
	public String addNewRoom(@RequestBody RoomDto roomDto) {
		Room building = roomService.getByRoomName(roomDto.getCheckedBuilding());
		Room room = null;
		List<Location> locationList = null;
		if (building != null) {
      System.out.println("building true");
			room = roomService.getByRoomNameAndPid(roomDto.getCheckedBuilding() + "-" + roomDto.getCheckedRoom(),building.getId());
		}
		if (room != null) {
			locationList = locationService.getByRoomId(room.getId());
		}
		if (locationList != null && locationList.size() > 0) {
			for (Location location : locationList) {
				if (Objects.equals(location.getPosition(),roomDto.getLocation())) {
					return "exist";
				}
			}
		}
		Location location = new Location();
		location.setPosition(roomDto.getLocation());
		location.setDescription(roomDto.getLocationDesc());
		if (building == null) {
			Room returnBuilding = roomService.insertBuilding(roomDto);
			roomDto.setPId(returnBuilding.getId());
			Room returnRoom = roomService.insertRoom(roomDto);
			location.setRoomId(returnRoom.getId());
		} else if (room == null) {
			roomDto.setPId(building.getId());
			Room returnRoom = roomService.insertRoom(roomDto);
			location.setRoomId(returnRoom.getId());
		} else {
			location.setRoomId(room.getId());
		}
		locationService.insert(location);
		return "success";
	}
	// 校验是否存在  未用
	@PostMapping("checkRoomIsExist")
	public String checkIsExist(@RequestBody RoomDto roomDto) {
		return null;
	}

	// 获取房间的具体信息 参数是房间名
	@GetMapping("getRoomInfo")
	public Room getRoomInfo(String name, Integer id) {
		return roomService.queryById(id);
	}
	// 更新房间信息
	@PutMapping("updateRoom")
	public String updateRoomInfo(@RequestBody RoomDto roomDto) {
		Room room = roomService.queryById(roomDto.getId());
		if (room != null && Objects.equals(room.getName(), roomDto.getInfoRoom()) && Objects.equals(room.getDescription(),roomDto.getInfoRoomDesc())) {
			return "exist";
		}
		assert room != null;
		room.setName(roomDto.getInfoRoom());
		room.setDescription(roomDto.getInfoRoomDesc());
		roomService.update(room);
		return "success";
	}
	// 删除一个房间
	@DeleteMapping("deleteRoom")
	public String deleteRoom(Integer id) {
		roomService.deleteById(id);
		roomService.deleteByPid(id);
		return "success";
	}
}