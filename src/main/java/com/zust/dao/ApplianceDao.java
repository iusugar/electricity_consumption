package com.zust.dao;

import com.zust.entity.Appliance;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Appliance)表数据库访问层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface ApplianceDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Appliance queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Appliance> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param appliance 实例对象
     * @return 对象列表
     */
    List<Appliance> queryAll(Appliance appliance);

    /**
     * 新增数据
     *
     * @param appliance 实例对象
     * @return 影响行数
     */
    int insert(Appliance appliance);

    /**
     * 修改数据
     *
     * @param appliance 实例对象
     * @return 影响行数
     */
    int update(Appliance appliance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}