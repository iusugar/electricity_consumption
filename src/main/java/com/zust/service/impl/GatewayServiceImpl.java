package com.zust.service.impl;

import com.zust.entity.Gateway;
import com.zust.dao.GatewayDao;
import com.zust.service.GatewayService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Gateway)表服务实现类
 *
 * @author makejava
 * @since 2021-11-18 16:01:42
 */
@Service("gatewayService")
public class GatewayServiceImpl implements GatewayService {
    @Resource
    private GatewayDao gatewayDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Gateway queryById(Integer id) {
        return this.gatewayDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Gateway> queryAllByLimit(int offset, int limit) {
        return this.gatewayDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param gateway 实例对象
     * @return 实例对象
     */
    @Override
    public Gateway insert(Gateway gateway) {
        this.gatewayDao.insert(gateway);
        return gateway;
    }

    /**
     * 修改数据
     *
     * @param gateway 实例对象
     * @return 实例对象
     */
    @Override
    public Gateway update(Gateway gateway) {
        this.gatewayDao.update(gateway);
        return this.queryById(gateway.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.gatewayDao.deleteById(id) > 0;
    }
}