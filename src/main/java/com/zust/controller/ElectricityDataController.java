package com.zust.controller;

import com.zust.entity.ElectricityData;
import com.zust.service.ElectricityDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* (ElectricityData)表控制层
*
* @author iusugar
* @since 2021-11-18 16:01:42
*/
@RestController
@RequestMapping("electricityData")
public class ElectricityDataController {
  /**
   * 服务对象
   */
  @Resource
  private ElectricityDataService electricityDataService;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public ElectricityData selectOne(Integer id) {
      return this.electricityDataService.queryById(id);
  }

	@GetMapping("getYesterdayData")
	public List<ElectricityData> getYesterdayEachHourData(Integer devId) {
    System.out.println(devId);
		return electricityDataService.getAppointDayData(new Date(),devId);
	}

}