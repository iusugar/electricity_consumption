package com.zust.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * (Room)实体类
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@Data
public class Room implements Serializable {
    private static final long serialVersionUID = -96814753259069139L;
    
    private Integer id;
    /**
    * 楼号、门牌号
    */
    private String name;
    /**
    * 描述
    */
    private String description;
    /**
    * 父节点Id
    */
    private Integer pId;

}