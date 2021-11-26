package com.zust.dao;

import com.zust.entity.ElectricityData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * (ElectricityData)表数据库访问层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface ElectricityDataDao {

	/**
	 * 通过指定日期查询
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