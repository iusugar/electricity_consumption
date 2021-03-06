package com.zust.service;

import com.zust.entity.Appliance;
import java.util.List;

/**
 * (Appliance)表服务接口
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface ApplianceService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Appliance queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Appliance> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param appliance 实例对象
     * @return 实例对象
     */
    Appliance insert(Appliance appliance);

    /**
     * 修改数据
     *
     * @param appliance 实例对象
     * @return 实例对象
     */
    Appliance update(Appliance appliance);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}