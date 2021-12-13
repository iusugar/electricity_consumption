package com.zust.service.impl;

import com.zust.dto.RoomDto;
import com.zust.entity.Room;
import com.zust.dao.RoomDao;
import com.zust.service.RoomService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* (Room)表服务实现类
*
* @author iusugar
* @since 2021-11-18 16:01:42
*/
@Service("roomService")
public class RoomServiceImpl implements RoomService {
  @Resource
  private RoomDao roomDao;


	/**
	 * 通过房间名和pid查询单条数据
	 *
	 * @param roomName 房间名
	 * @param pId      父级ID
	 * @return 实例对象
	 */
	@Override
	public Room getByRoomNameAndPid(String roomName, Integer pId) {
		return roomDao.queryByNameAndPid(roomName,pId);
	}

	/**
	 * 通过房间名查询
	 *
	 * @param name 房间名
	 * @return 实例对象
	 */
	@Override
	public Room getByRoomName(String name) {
		return roomDao.queryByRoomName(name);
	}

	/**
	 * 添加一条房间数据
	 *
	 * @param roomDto 数据传输对象
	 * @return 实例对象
	 */
	@Override
	public Room insertRoom(RoomDto roomDto) {
		Room room = new Room();
		room.setName(roomDto.getCheckedBuilding()+ "-" +roomDto.getCheckedRoom());
		room.setDescription(roomDto.getRoomDesc());
		room.setPId(roomDto.getPId());
		roomDao.insert(room);
		return room;
	}

	/**
	 * 添加一条楼号数据
	 *
	 * @param roomDto 数据传输对象
	 * @return 实例对象
	 */
	@Override
	public Room insertBuilding(RoomDto roomDto) {
		Room building = new Room();
		building.setName(roomDto.getCheckedBuilding());
		building.setDescription(roomDto.getBuildingDesc());
		roomDao.insert(building);
		return building;
	}

	/**
	 * 查询有设备存在的所有房间集合
	 *
	 * @return 传输对象集合
	 */
	@Override
	public List<RoomDto> getAllRoomByDevIdList(List<Integer> devIdList) {
		return roomDao.queryByDevIdList(devIdList);
	}


	/**
	 * transfer组件传值
	 * 添加一个或者多个房间
	 * @param l 房间列表
	 * @return 成功消息
	 */
  public String insert(List<String> l) {
		String building = l.get(0).substring(0,l.get(0).indexOf("-"));
		Room returnRoom = roomDao.queryByBuildingNum(building);
	  Room newRoom = new Room();
		if (returnRoom != null) {
			for (String str : l) {
				if (roomDao.queryByBuildingNum(str) != null) {
				} else {
					newRoom.setName(str);
					newRoom.setPId(returnRoom.getId());
					roomDao.insert(newRoom);
				}
			}
		} else {
			newRoom.setName(building);
			roomDao.insert(newRoom);
      for (String str : l) {
				Room r = new Room();
				r.setName(str);
				r.setPId(newRoom.getId());
				roomDao.insert(r);
      }
		}
    return "success";
  }

  /**
   * 查询全部数据
   */
  @Override
  public List<Room> queryAllRoom() {
    return roomDao.queryAllRoom();
  }

  //
  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public Room queryById(Integer id) {
    return this.roomDao.queryById(id);
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<Room> queryAllByLimit(int offset, int limit) {
    return this.roomDao.queryAllByLimit(offset, limit);
  }

  /**
   * 新增数据
   *
   * @param room 实例对象
   * @return 实例对象
   */
  @Override
  public Room insert(Room room) {
    this.roomDao.insert(room);
    return room;
  }

  /**
   * 修改数据
   *
   * @param room 实例对象
   * @return 实例对象
   */
  @Override
  public Room update(Room room) {
    this.roomDao.update(room);
    return this.queryById(room.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Integer id) {
    return this.roomDao.deleteById(id) > 0;
  }
}