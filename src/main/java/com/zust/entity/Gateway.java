package com.zust.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * (Gateway)实体类
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@Data
public class Gateway implements Serializable {
    private static final long serialVersionUID = -70153188977776197L;
    
    private Integer id;
    /**
    * 网关
    */
    private String name;
    /**
    * 数据描述
    */
    private String description;
    /**
    * 最后更新时间
    */
    private Date updateTime;
    /**
    * 创建时间
    */
    private Date createTime;

}