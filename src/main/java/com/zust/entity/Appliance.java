package com.zust.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Appliance)实体类
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@Data
public class Appliance implements Serializable {
    private static final long serialVersionUID = 237275585778129733L;
    /**
    * 用电器表id
    */
    private Integer id;
    /**
    * 插座id
    */
    private Integer devId;
    /**
    * 用电器名称
    */
    private String name;
    /**
    * 用途描述
    */
    private String description;
    /**
    * 接入时间
    */
    private Date createTime;

}