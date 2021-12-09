package com.zust.service.impl;

import com.zust.dto.LocationDto;
import com.zust.entity.Location;
import com.zust.dao.LocationDao;
import com.zust.service.LocationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
	* (Location)表服务实现类
	*
	* @author iusugar
	* @since 2021-11-18 16:01:42
*/
@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Resource
	private LocationDao locationDao;

	/**
	 * 查询所有数据
	 *
	 * @return 所有数据列表
	 */
	@Override
	public List<Location> getAllLocation() {
		return null;
	}

	/**
	 * 通过设备ID主键查找具体位置
	 * @param l 设备主键ID列表
	 * @return 带设备ID的位置列表
	 */
	@Override
	public List<LocationDto> getLocationByDevIdList(List<Integer> l) {
//    System.out.println("执行queryByDevIdList");
		List<LocationDto> m =locationDao.queryByDevIdList(l);
    System.out.println(m);
		return null;
	}

	/**
	 * 通过roomId查询单条数据
	 * @param roomId 房间号字段
	 * @return 实例对象
	 */
	@Override
	public Location queryByRoomIdPosition(Integer roomId, String position) {
		return locationDao.queryByRoomIdPosition(roomId,position);
	}

	/**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public Location queryById(Integer id) {
      return this.locationDao.queryById(id);
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<Location> queryAllByLimit(int offset, int limit) {
      return this.locationDao.queryAllByLimit(offset, limit);
  }

  /**
   * 新增数据
   *
   * @param location 实例对象
   * @return 实例对象
   */
  @Override
  public Location insert(Location location) {
      this.locationDao.insert(location);
      return location;
  }

  /**
   * 修改数据
   *
   * @param location 实例对象
   * @return 实例对象
   */
  @Override
  public Location update(Location location) {
      this.locationDao.update(location);
      return this.queryById(location.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Integer id) {
      return this.locationDao.deleteById(id) > 0;
  }
}