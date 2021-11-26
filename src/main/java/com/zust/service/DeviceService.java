package com.zust.service;

import com.zust.dto.DeviceDto;
import com.zust.entity.Device;
import java.util.List;

/**
 * (Device)表服务接口
 *
 * @author makejava
 * @since 2021-11-18 16:01:42
 */
public interface DeviceService {

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