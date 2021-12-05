package com.zust.service.impl;

import com.zust.entity.Appliance;
import com.zust.dao.ApplianceDao;
import com.zust.service.ApplianceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Appliance)表服务实现类
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@Service("applianceService")
public class ApplianceServiceImpl implements ApplianceService {
    @Resource
    private ApplianceDao applianceDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Appliance queryById(Integer id) {
        return this.applianceDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Appliance> queryAllByLimit(int offset, int limit) {
        return this.applianceDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param appliance 实例对象
     * @return 实例对象
     */
    @Override
    public Appliance insert(Appliance appliance) {
        this.applianceDao.insert(appliance);
        return appliance;
    }

    /**
     * 修改数据
     *
     * @param appliance 实例对象
     * @return 实例对象
     */
    @Override
    public Appliance update(Appliance appliance) {
        this.applianceDao.update(appliance);
        return this.queryById(appliance.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.applianceDao.deleteById(id) > 0;
    }
}