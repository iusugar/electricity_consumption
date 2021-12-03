package com.zust.service.impl;

import com.zust.dao.DeviceStatusDao;
import com.zust.dao.LocationDao;
import com.zust.dao.RoomDao;
import com.zust.dto.DeviceDto;
import com.zust.dto.LocationDto;
import com.zust.dto.RoomDto;
import com.zust.entity.Device;
import com.zust.dao.DeviceDao;
import com.zust.entity.DeviceStatus;
import com.zust.entity.Location;
import com.zust.entity.Room;
import com.zust.service.DeviceService;
import com.zust.service.LocationService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.expr.NewArray;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.stream.events.DTD;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
* (Device)表服务实现类
*
* @author iusugar
* @since 2021-11-18 16:01:42
*/
@Slf4j
@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {
  @Resource
  private DeviceDao deviceDao;
	@Resource
	private RoomDao roomDao;
	@Resource
	private LocationDao locationDao;
	@Resource
	private DeviceStatusDao deviceStatusDao;


	/**
	 * 通过设备ID删除
	 *
	 * @param deviceId 设备ID
	 * @return 是否成功
	 */
	@Override
	public String deleteByDeviceId(String deviceId) {
		deviceDao.queryByDevId(deviceId);
		return null;
	}

	/**
	 * 更新设备信息
	 *
	 * @param deviceDto 数据传输对象
	 * @return 更新成功或失败消息
	 */
	@Override
	public String updateDevice(DeviceDto deviceDto) {
		Device device = deviceDao.queryByDevId(deviceDto.getDeviceId());
		Room room = roomDao.queryByBuildingNum(deviceDto.getBuildNum() + "-" + deviceDto.getRoomNum());
    DeviceStatus deviceStatus = deviceStatusDao.queryByDevId(device.getId());
		Location location = locationDao.queryByRoomIdPosition(room.getId(), deviceDto.getLocation());
		// 判断是否为空 如果位置不存在就新建
		if (location == null) {
			Location l = new Location();
			l.setRoomId(room.getId());
			l.setPosition(deviceDto.getLocation());
			locationDao.insert(l);
			deviceStatus.setLocaId(l.getId());
			deviceStatusDao.update(deviceStatus);
		} else {
			if (Objects.equals(deviceStatus.getLocaId(), location.getId())) {
				log.info("相同位置");
				return "same";
			} else {
				deviceStatus.setLocaId(location.getId());
				deviceStatusDao.update(deviceStatus);
			}
		}
		return "success";
	}

	/**
	 * 通过条件查询
	 * @param usageDesc 用途
	 * @param deviceId 设备ID
	 * @param buildNum 楼号
	 * @param roomNum 房间号
	 * @return 符合条件的实例对象集合
	 */
	@Override
	public List<DeviceDto> getByOptions(String usageDesc, String deviceId, String buildNum, String roomNum) {
		String location = "";
		if (buildNum != null && !buildNum.equals("") && roomNum !=null && !roomNum.equals("")) {
			location = buildNum + "-" + roomNum;
		} else if (buildNum != null && !buildNum.equals("")) {
			location = buildNum;
		} else {
			location = roomNum;
		}
		List<DeviceDto> deviceDtoList = deviceDao.queryByOptions(usageDesc,deviceId,location);
		if (deviceDtoList != null) {
			deviceDtoList.forEach(dto -> {
				dto.setBuildNum(dto.getRoomNum().substring(0,dto.getRoomNum().indexOf("-")));
				String num = dto.getRoomNum().substring(dto.getRoomNum().indexOf("-") + 1);
				dto.setRoomNum(num);
			});
		}
//		deviceDtoList.forEach(System.out::println);
		return deviceDtoList;
	}

	/**
	 * 查询所有设备
	 *
	 * @return 所有设备对象集合
	 */
	@Override
	public List<DeviceDto> getAllDevice() {
		List<Device> deviceList = deviceDao.queryAllDevice();
		List<RoomDto> roomDtoList;
		List<DeviceDto> dtoList = new ArrayList<>();
		if (deviceList != null && deviceList.size() > 0) {
			List<Integer> idList = deviceList.stream().map(device -> device.getId()).collect(Collectors.toList());
			roomDtoList = roomDao.queryByDevIdList(idList);
			for (int i = 0; i < deviceList.size(); i++) {
				DeviceDto deviceDto = new DeviceDto();
				BeanUtils.copyProperties(deviceList.get(i),deviceDto);
				String buildNum = roomDtoList.get(i).getName().substring(0,roomDtoList.get(i).getName().indexOf("-"));
				String roomNum = roomDtoList.get(i).getName().substring(roomDtoList.get(i).getName().indexOf("-") + 1);
				deviceDto.setBuildNum(buildNum);
				deviceDto.setRoomNum(roomNum);
				deviceDto.setLocation(roomDtoList.get(i).getPosition());
				dtoList.add(deviceDto);
			}
		}
		return dtoList;
	}

	/**
	 * 根据房间门牌号查询
	 *
	 * @param roomNum 房间门牌号
	 * @return 对象列表
	 */
	@Override
	public List<DeviceDto> queryByRoomNum(String roomNum) {
		String buildNum = roomNum.substring(0,roomNum.indexOf('-'));
		List<Device> l = deviceDao.queryByRoomNum(roomNum);
		List<DeviceDto> dtoList = new ArrayList<>();
		if (l != null && l.size() > 0) {
			List<Integer> idList = l.stream().map(device -> device.getId()).collect(Collectors.toList());
			for (Device device : l) {
				DeviceDto deviceDto = new DeviceDto();
				BeanUtils.copyProperties(device, deviceDto);
				deviceDto.setBuildNum(buildNum);
				deviceDto.setRoomNum(roomNum);
				dtoList.add(deviceDto);
			}
			dtoList.sort((a,b) -> Integer.compare(a.getId(),b.getId()));
			List<LocationDto> locationDtoList = locationDao.queryByDevIdList(idList);
			for (int i = 0; i < dtoList.size(); i++) {
				dtoList.get(i).setLocation(locationDtoList.get(i).getPosition());
				dtoList.get(i).setCurrentState(deviceStatusDao.queryByDevId(dtoList.get(i).getId()).getCurrentState());
			}
//      locationDtoList.forEach(System.out::println);
//			dtoList.forEach(System.out::println);
			return dtoList;
		}
		return null;
	}

	/**
	 * 新增数据
	 *
	 * @param dto 传输对象
	 * @return 实例对象
	 */
	@Override
	public Device insert(DeviceDto dto) {
		Device device = new Device();
		BeanUtils.copyProperties(dto,device);
		for (String str : dto.getCheckedFunctions()) {
			if (Objects.equals(str, "监测电流")) {
				device.setCurrentMonitor(1);
			} else if (Objects.equals(str, "监测电压")) {
				device.setVoltageMonitor(1);
			} else if (Objects.equals(str, "监测功率")) {
				device.setPowerMonitor(1);
			} else if (Objects.equals(str, "远程控制")) {
				device.setRemoteControl(1);
			} else if (Objects.equals(str, "记录电量")) {
				device.setElecConsum(1);
			} else if (Objects.equals(str, "红外功能")) {
				device.setInfraredFunc(1);
			}
		}
		device.setCreateTime(new Date());
		deviceDao.insert(device);
		Room room = roomDao.queryByBuildingNum(dto.getRoomNum());
		// 搜索位置是否存在，存在不需要再添加，直接使用ID
		Location location = new Location();
		Location resultLocation = locationDao.queryByRoomIdPosition(room.getId(),dto.getLocation());
		if ( resultLocation != null ) {
			System.out.println("已存在");
		} else {
			location.setRoomId(room.getId());
			location.setPosition(dto.getLocation());
			locationDao.insert(location);
		}
		DeviceStatus deviceStatus = new DeviceStatus();
		deviceStatus.setDevId(device.getId());
		if (resultLocation != null) {
			deviceStatus.setLocaId(resultLocation.getId());
		} else {
			deviceStatus.setLocaId(location.getId());
		}
		deviceStatus.setGwId(1);
		deviceStatus.setCurrentState(0);
		deviceStatusDao.insert(deviceStatus);
		return null;
	}

	/**
   * 通过devID查询单条数据
   *
   * @param devId 设备id
   * @return 实例对象
   */
  @Override
  public Device queryByDeviceId(String devId) {
      return this.deviceDao.queryByDevId(devId);
  }


  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public Device queryById(Integer id) {
      return this.deviceDao.queryById(id);
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<Device> queryAllByLimit(int offset, int limit) {
      return this.deviceDao.queryAllByLimit(offset, limit);
  }

  /**
   * 新增数据
   *
   * @param device 实例对象
   * @return 实例对象
   */
  @Override
  public Device insert(Device device) {
      this.deviceDao.insert(device);
      return device;
  }

  /**
   * 修改数据
   *
   * @param device 实例对象
   * @return 实例对象
   */
  @Override
  public Device update(Device device) {
      this.deviceDao.update(device);
      return this.queryById(device.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Integer id) {
      return this.deviceDao.deleteById(id) > 0;
  }
}