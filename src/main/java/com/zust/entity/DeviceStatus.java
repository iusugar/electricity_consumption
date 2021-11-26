package com.zust.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (DeviceStatus)实体类
 *
 * @author makejava
 * @since 2021-11-26 14:15:13
 */
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Date getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(Date lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public Object getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Object currentState) {
        this.currentState = currentState;
    }

    public Integer getLocaId() {
        return locaId;
    }

    public void setLocaId(Integer locaId) {
        this.locaId = locaId;
    }

    public Integer getGwId() {
        return gwId;
    }

    public void setGwId(Integer gwId) {
        this.gwId = gwId;
    }

}