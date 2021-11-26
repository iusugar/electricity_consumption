package com.zust.controller;

import com.zust.entity.DeviceStatus;
import com.zust.service.DeviceStatusService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (DeviceStatus)表控制层
 *
 * @author makejava
 * @since 2021-11-26 14:15:13
 */
@RestController
@RequestMapping("deviceStatus")
public class DeviceStatusController {
    /**
     * 服务对象
     */
    @Resource
    private DeviceStatusService deviceStatusService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public DeviceStatus selectOne(Integer id) {
        return this.deviceStatusService.queryById(id);
    }

}