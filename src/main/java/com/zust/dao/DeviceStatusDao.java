package com.zust.dao;

import com.zust.entity.DeviceStatus;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (DeviceStatus)表数据库访问层
 *
 * @author iusugar
 * @since 2021-11-26 14:15:13
 */
public interface DeviceStatusDao {

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
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<DeviceStatus> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param deviceStatus 实例对象
   * @return 对象列表
   */
  List<DeviceStatus> queryAll(DeviceStatus deviceStatus);

  /**
   * 新增数据
   *
   * @param deviceStatus 实例对象
   * @return 影响行数
   */
  int insert(DeviceStatus deviceStatus);

  /**
   * 修改数据
   *
   * @param deviceStatus 实例对象
   * @return 影响行数
   */
  int update(DeviceStatus deviceStatus);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

}