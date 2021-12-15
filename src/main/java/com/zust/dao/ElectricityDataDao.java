package com.zust.dao;

import com.zust.entity.ElectricityData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (ElectricityData)表数据库访问层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface ElectricityDataDao {

	/**
	 * 获取指定ID设备在指定一天的用电量
	 * @param day 天数差值
	 * @param devId 设备主键ID
	 * @return 用电量
	 */
	Object weekEachDayConsumptionById(Integer day, Integer devId);

	/**
	 * 查询最近一周有用电记录的插座
	 * @return 实例对象列表
	 */
	List<ElectricityData> queryWeekHaveUsageHistory();

	/**
	 * 获取指定ID插座一周用电量
	 * @param devId 设备主键ID
	 * @return 用电量
	 */
	Object weekConsumptionByDevId(Integer devId);

	/**
	 * 通过设备主键查询去年每月最后一条用电数据
	 * @param devId 设备主键
	 * @return 单个设备的每月用电数据列表
	 */
	List<ElectricityData> lastYearMonthLastConsumptionById(Integer devId);

	/**
	 * 通过设备主键查询去年每月第一条用电数据
	 * @param devId 设备主键
	 * @return 单个设备的每月用电数据列表
	 */
	List<ElectricityData> lastYearMonthFirstConsumptionById(Integer devId);

	/**
	 * 通过设备主键查询昨日用电量
	 * @param devId 设备主键
	 * @return 昨日用电量
	 */
	Object yesterdayConsumptionById(Integer devId);

	/**
	 * 通过设备主键查询今日已用电量
	 * @param devId 设备主键
	 * @return 今日用电量
	 */
	Object todayConsumptionById(Integer devId);

	/**
	 * 通过设备主键查询上月已使用电量
	 * @param devId 设备主键
	 * @return 上月使用电量
	 */
	Object lastMonthConsumptionByDevId(Integer devId);

	/**
	 * 通过设备主键ID查询当前月份已使用电量
	 * @param devId 设备主键
	 * @return 当前月用电量
	 */
	Object currentMonthConsumptionByDevId(Integer devId);

	/**
	 * 通过设备主键ID查询
	 * @param devId 设备主键
	 * @return 实例对象
	 */
	ElectricityData queryByDevId(Integer devId);

	/**
	 * 通过指定日期查询 24小时
	 * @param startTime 开始时间
	 * @param endTime 结束时间
	 * @param devId 设备主键ID
	 * @return 实例对象集合
	 */
	List<ElectricityData> queryByDate(@Param("start") String startTime,@Param("end") String endTime, Integer devId);


  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  ElectricityData queryById(Integer id);

  /**
   * 查询指定行数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<ElectricityData> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


  /**
   * 通过实体作为筛选条件查询
   *
   * @param electricityData 实例对象
   * @return 对象列表
   */
  List<ElectricityData> queryAll(ElectricityData electricityData);

  /**
   * 新增数据
   *
   * @param electricityData 实例对象
   * @return 影响行数
   */
  int insert(ElectricityData electricityData);

  /**
   * 修改数据
   *
   * @param electricityData 实例对象
   * @return 影响行数
   */
  int update(ElectricityData electricityData);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 影响行数
   */
  int deleteById(Integer id);

}