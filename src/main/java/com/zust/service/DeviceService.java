package com.zust.service;

import com.zust.dto.DeviceDto;
import com.zust.entity.Device;
import java.util.List;

/**
 * (Device)表服务接口
 *
 * @author iusuagr
 * @since 2021-11-18 16:01:42
 */
public interface DeviceService {

	/**
	 * 通过网关查询设备
	 * @param gateway 网关名
	 * @return 对象列表
	 */
	List<Device> getDeviceByGateway(String gateway);

	/**
	 * 通过时间点查询在线设备
	 * @param day 一周中的第几天
	 * @param hour 一天中的第几小时
	 * @return 设备列表
	 */
	List<Device> getOnlineDeviceByTimePoint(Integer day, Integer hour);

	/**
	 * 通过设备ID删除
	 * @param deviceId 设备ID
	 * @return 是否成功
	 */
	String deleteByDeviceId(String deviceId);

	/**
	 * 更新设备信息
	 * @param deviceDto 数据传输对象
	 * @return 更新成功或失败消息
	 */
	String updateDevice(DeviceDto deviceDto);

	/**
	 * 通过条件查询
	 * @param usageDesc 用途
	 * @param deviceId 设备ID
	 * @param buildNum 楼号
	 * @param roomNum 房间号
	 * @return 符合条件的实例对象集合
	 */
	List<DeviceDto> getByOptions(String usageDesc, String deviceId, String buildNum, String roomNum);

	/**
	 * 查询所有设备
	 * @return 所有实例对象集合
	 */
	List<DeviceDto> getAllDevice();

	/**
	 * 根据房间门牌号查询
	 * @param roomNum 房间门牌号
	 * @return 对象列表
	 */
	List<DeviceDto> queryByRoomNum(String roomNum);

	/**
	 * 新增数据
	 *
	 * @param deviceDto 传输对象
	 * @return 实例对象
	 */
	Device insert(DeviceDto deviceDto);

  /**
   * 通过devID查询单条数据
   *
   * @param devId 设备id
   * @return 实例对象
   */
  Device queryByDeviceId(String devId);

  //
  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  Device queryById(Integer id);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<Device> queryAllByLimit(int offset, int limit);

  /**
   * 新增数据
   *
   * @param device 实例对象
   * @return 实例对象
   */
  Device insert(Device device);

  /**
   * 修改数据
   *
   * @param device 实例对象
   * @return 实例对象
   */
  Device update(Device device);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

}