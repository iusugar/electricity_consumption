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

	@PostMapping("addNew")
	public String addNewRoom(@RequestBody RoomDto roomDto) {
    System.out.println(roomDto);
		Room building = roomService.getByRoomName(roomDto.getCheckedBuilding());
		Room room = null;
		List<Location> locationList = null;
		if (building != null) {
			room = roomService.getByRoomNameAndPid(roomDto.getCheckedRoom(),building.getId());
		}
		if (room != null) {
			locationList = locationService.getByRoomId(room.getId());
		}
    System.out.println(locationList);
		if (locationList != null && locationList.size() > 0) {
			for (Location location : locationList) {
				if (Objects.equals(location.getPosition(),roomDto.getLocation())) {
					System.out.println("存在true");
					return "exist";
				}
			}
		}

		if (building == null) {
			Room returnBuilding = roomService.insertBuilding(roomDto);
			roomDto.setPId(returnBuilding.getId());
			Room returnRoom = roomService.insertRoom(roomDto);
			Location location = new Location();
			location.setRoomId(returnRoom.getId());
			location.setPosition(roomDto.getLocation());
			location.setDescription(roomDto.getLocationDesc());
			locationService.insert(location);
		} else if (room == null) {
			roomDto.setPId(building.getId());
			Room returnRoom = roomService.insertRoom(roomDto);
			Location location = new Location();
			location.setRoomId(returnRoom.getId());
			location.setPosition(roomDto.getLocation());
			location.setDescription(roomDto.getLocationDesc());
			locationService.insert(location);
		} else {
			Location location = new Location();
			location.setRoomId(room.getId());
			location.setPosition(roomDto.getLocation());
			location.setDescription(roomDto.getLocationDesc());
			locationService.insert(location);
		}
		return "success";
	}
	@PostMapping("checkRoomIsExist")
	public String checkIsExist(@RequestBody RoomDto roomDto) {
		return null;
	}
}