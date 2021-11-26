package com.zust.controller;

import com.zust.entity.Appliance;
import com.zust.service.ApplianceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Appliance)表控制层
 *
 * @author makejava
 * @since 2021-11-18 16:01:42
 */
@RestController
@RequestMapping("appliance")
public class ApplianceController {
  /**
   * 服务对象
   */
  @Resource
  private ApplianceService applianceService;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public Appliance selectOne(Integer id) {
      return this.applianceService.queryById(id);
  }

}