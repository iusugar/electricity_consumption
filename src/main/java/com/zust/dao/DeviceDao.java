package com.zust.dao;

import com.zust.entity.Device;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Device)表数据库访问层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface DeviceDao {

	/**
	 * 通过房间号查询
	 * @param roomNum 房间门牌号
	 * @return 返回对象列表
	 */
	List<Device> queryByRoomNum(String roomNum);

	/**
	 * 通过设备ID查询
	 * @param devId 设备自身ID
	 * @return 实例对象
	 */
  Device queryByDevId(String devId);


  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  Device queryById(Integer id);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<Device> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param device 实例对象
   * @return 对象列表
   */
  List<Device> queryAll(Device device);

  /**
   * 新增数据
   *
   * @param device 实例对象
   * @return 影响行数
   */
  int insert(Device device);

  /**
   * 修改数据
   *
   * @param device 实例对象
   * @return 影响行数
   */
  int update(Device device);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

}