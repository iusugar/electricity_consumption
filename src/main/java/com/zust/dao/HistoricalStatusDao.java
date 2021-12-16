package com.zust.dao;

import com.zust.entity.HistoricalStatus;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (HistoricalStatus)表数据库访问层
 *
 * @author iusugar
 * @since 2021-12-07 09:33:43
 */
public interface HistoricalStatusDao {


	/**
	 * 查找插座本周每天每个小时之前最新的一条记录 用于统计每小时插座在线数量
	 * @param devId 设备主键ID
	 * @param day 值为0-6 选择今日在内的近七天
	 * @param hour 值为0-23 选择小时
	 * @param week 值为0或1 选择本周或上周 1表示上周
	 * @return 实例对象
	 */
	HistoricalStatus queryWeekHourOnlineByDevId(Integer devId,Integer day,Integer hour,Integer week);

	// 弃用 换另一个
	/**
	 * 查询本周指定一天指定小时在线设备数量
	 * @param day 值为0-7 选择周一到周日
	 * @param hour 值为0-23 选择小时
	 * @param week 值为0或1 选择本周或上周 1表示上周
	 * @return 设备数量
	 */
	Integer queryWeekEachHourOnlineCount(Integer day,Integer hour,Integer week);

	/**
	 * 通过设备主键ID查询单条数据
	 * @param devId 设备主键ID
	 * @return 实例对象
	 */
	HistoricalStatus queryByDevId(Integer devId);

  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  HistoricalStatus queryById(Integer id);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<HistoricalStatus> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param historicalStatus 实例对象
   * @return 对象列表
   */
  List<HistoricalStatus> queryAll(HistoricalStatus historicalStatus);

  /**
   * 新增数据
   *
   * @param historicalStatus 实例对象
   * @return 影响行数
   */
  int insert(HistoricalStatus historicalStatus);

  /**
   * 修改数据
   *
   * @param historicalStatus 实例对象
   * @return 影响行数
   */
  int update(HistoricalStatus historicalStatus);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

}