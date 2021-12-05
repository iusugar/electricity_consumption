package com.zust.service;

import com.zust.entity.ElectricityData;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (ElectricityData)表服务接口
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface ElectricityDataService {

	/**
	 * 获取昨日用电量
	 * @return 用电量
	 */
	float getYesterdayConsumption();

	/**
	 * 获取今日用电量
	 * @return 用电量
	 */
	float getTodayConsumption();

	/**
	 * 获取上个月用电量
	 * @return 用电量
	 */
	float getLastMonthConsumption();

	/**
	 * 获取当前月份用电量
	 * @return 用电量
	 */
	float getCurrentMonthConsumption();

	/**
	 * 通过日期和设备主键ID查询昨日24时用电数据
	 * @param date 日期
	 * @param devId 设备主键ID
	 * @return 实例对象集合
	 */
	List<ElectricityData> getAppointDayData(Date date,Integer devId);


  /**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  ElectricityData queryById(Integer id);

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  List<ElectricityData> queryAllByLimit(int offset, int limit);

  /**
   * 新增数据
   *
   * @param electricityData 实例对象
   * @return 实例对象
   */
  ElectricityData insert(ElectricityData electricityData);

  /**
   * 修改数据
   *
   * @param electricityData 实例对象
   * @return 实例对象
   */
  ElectricityData update(ElectricityData electricityData);

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  boolean deleteById(Integer id);

}