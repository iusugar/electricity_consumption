package com.zust.service;

import com.zust.entity.DeviceStatus;
import java.util.List;

/**
 * (DeviceStatus)表服务接口
 *
 * @author iusugar
 * @since 2021-11-26 14:15:13
 */
public interface DeviceStatusService {

	/**
	 * 通过设备主键ID查询单条数据
	 * @param devId 设备主键ID
	 * @return 实例对象
	 */
	DeviceStatus queryByDevId(Integer devId);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  DeviceStatus queryById(Integer id);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<DeviceStatus> queryAllByLimit(int offset, int limit);

  /**
   * 新增数据
   *
   * @param deviceStatus 实例对象
   * @return 实例对象
   */
  DeviceStatus insert(DeviceStatus deviceStatus);

  /**
   * 修改数据
   *
   * @param deviceStatus 实例对象
   * @return 实例对象
   */
  DeviceStatus update(DeviceStatus deviceStatus);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

}