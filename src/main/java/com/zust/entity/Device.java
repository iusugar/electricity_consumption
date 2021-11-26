package com.zust.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Device)实体类
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@Data
public class Device implements Serializable {
    private static final long serialVersionUID = 165410358215346502L;
    
    private Integer id;
    /**
    * 设备ID
    */
    private String deviceId;
    /**
    * 用途，用来说明负载
    */
    private String usageDesc;
    /**
    * 额定电压
    */
    private float ratedVoltage;
    /**
    * 额定电流
    */
    private float ratedCurrent;
    /**
    * 额定功率
    */
    private float ratedPower;
    /**
    * 添加插座的时间

    */
    private Date createTime;
    /**
    * 电压监测功能
    */
    private int voltageMonitor;
    /**
    * 电流监测功能
    */
    private int currentMonitor;
    /**
    * 功率监测功能
    */
    private int powerMonitor;
    /**
    * 远程控制
    */
    private int remoteControl;
    /**
    * 用电量记录
    */
    private int elecConsum;
    /**
    * 红外功能
    */
    private int infraredFunc;

}