package com.zust.service;

import com.zust.dto.LocationDto;
import com.zust.entity.Location;
import java.util.List;
import java.util.Map;

/**
 * (Location)表服务接口
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface LocationService {

	/**
	 * 通过设备ID主键查找具体位置
	 * @param l 设备主键ID列表
	 * @return 带设备ID的位置列表
	 */
	List<LocationDto> getLocationByDevIdList(List<Integer> l);

	/**
	 *
	 * @param roomId 房间ID主键
	 * @param position 具体位置
	 * @return 对象实例
	 */
	Location queryByRoomIdPosition(Integer roomId, String position);



  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  Location queryById(Integer id);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<Location> queryAllByLimit(int offset, int limit);

  /**
   * 新增数据
   *
   * @param location 实例对象
   * @return 实例对象
   */
  Location insert(Location location);

  /**
   * 修改数据
   *
   * @param location 实例对象
   * @return 实例对象
   */
  Location update(Location location);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

}