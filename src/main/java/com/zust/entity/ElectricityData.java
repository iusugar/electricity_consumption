package com.zust.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (ElectricityData)实体类
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@Data
public class ElectricityData implements Serializable {
  private static final long serialVersionUID = -44927854190774693L;

  private Integer id;
  /**
  * 插座id
  */
  private Integer devId;
  /**
  * 定时获取数据的时间
  */
  private Date createTime;
  /**
  * 瞬时电压
  */
  private Object insVoltage;
  /**
  * 瞬时电流
  */
  private Object insCurrent;
  /**
   * 瞬时电流
   */
  private Object insPower;
	/**
	 * 用电量
	 */
	private Object consumption;

}