package com.zust.service.impl;

import com.zust.entity.DeviceStatus;
import com.zust.dao.DeviceStatusDao;
import com.zust.service.DeviceStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (DeviceStatus)表服务实现类
 *
 * @author makejava
 * @since 2021-11-26 14:15:13
 */
@Service("deviceStatusService")
public class DeviceStatusServiceImpl implements DeviceStatusService {
    @Resource
    private DeviceStatusDao deviceStatusDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DeviceStatus queryById(Integer id) {
        return this.deviceStatusDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DeviceStatus> queryAllByLimit(int offset, int limit) {
        return this.deviceStatusDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param deviceStatus 实例对象
     * @return 实例对象
     */
    @Override
    public DeviceStatus insert(DeviceStatus deviceStatus) {
        this.deviceStatusDao.insert(deviceStatus);
        return deviceStatus;
    }

    /**
     * 修改数据
     *
     * @param deviceStatus 实例对象
     * @return 实例对象
     */
    @Override
    public DeviceStatus update(DeviceStatus deviceStatus) {
        this.deviceStatusDao.update(deviceStatus);
        return this.queryById(deviceStatus.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.deviceStatusDao.deleteById(id) > 0;
    }
}