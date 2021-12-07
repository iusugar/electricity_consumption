package com.zust.service;

import com.zust.entity.HistoricalStatus;
import java.util.List;

/**
 * (HistoricalStatus)表服务接口
 *
 * @author iusugar
 * @since 2021-12-07 09:33:43
 */
public interface HistoricalStatusService {

	/**
	 * 查询一周每个小时在线的设备数
	 * @return 在线数据
	 */
	Integer[][] getWeekEachHourOnlineNumber();

	/**
	 * 通过设备主键ID查询
	 * @param devId 设备主键ID
	 * @return 实例对象
	 */
	HistoricalStatus getByDevId(Integer devId);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  HistoricalStatus queryById(Integer id);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<HistoricalStatus> queryAllByLimit(int offset, int limit);

  /**
   * 新增数据
   *
   * @param historicalStatus 实例对象
   * @return 实例对象
   */
  HistoricalStatus insert(HistoricalStatus historicalStatus);

  /**
   * 修改数据
   *
   * @param historicalStatus 实例对象
   * @return 实例对象
   */
  HistoricalStatus update(HistoricalStatus historicalStatus);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

}