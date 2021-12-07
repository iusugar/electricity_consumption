package com.zust.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (HistoricalStatus)实体类
 *
 * @author iusugar
 * @since 2021-12-07 09:33:43
 */
@Data
public class HistoricalStatus implements Serializable {
  private static final long serialVersionUID = 206690564444206292L;
  /**
  * 主键
  */
  private Integer id;
  /**
  * 设备id
  */
  private Integer devId;
  /**
  * 创建时间
  */
  private Date createTime;
  /**
  * 设备状态
  */
  private Object status;

}