package com.zust.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (DeviceStatus)实体类
 *
 * @author iusugar
 * @since 2021-11-26 14:15:13
 */
@Data
public class DeviceStatus implements Serializable {
    private static final long serialVersionUID = 166272432686283557L;
    /**
    * 插座状态表id
    */
    private Integer id;
    /**
    * 插座id
    */
    private Integer devId;
    /**
    * 最近一次使用时间
    */
    private Date lastUseTime;
    /**
    * 当前状态，有负载或无负载
    */
    private Object currentState;
    /**
    * 具体位置的id
    */
    private Integer locaId;
    /**
    * 网关id
    */
    private Integer gwId;

}