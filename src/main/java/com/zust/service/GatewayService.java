package com.zust.service;

import com.zust.entity.Gateway;
import java.util.List;

/**
 * (Gateway)表服务接口
 *
 * @author makejava
 * @since 2021-11-18 16:01:42
 */
public interface GatewayService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Gateway queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Gateway> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param gateway 实例对象
     * @return 实例对象
     */
    Gateway insert(Gateway gateway);

    /**
     * 修改数据
     *
     * @param gateway 实例对象
     * @return 实例对象
     */
    Gateway update(Gateway gateway);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}