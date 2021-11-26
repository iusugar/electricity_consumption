package com.zust.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Location)实体类
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@Data
public class Location implements Serializable {
  private static final long serialVersionUID = 424880188804000040L;

  private Integer id;
  /**
  * 归属的房间
  */
  private Integer roomId;
  /**
  * 房间中的具体位置
  */
  private String position;
  /**
  * 对位置的描述
  */
  private String description;

}