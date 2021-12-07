package com.zust.controller;

import com.zust.entity.ElectricityData;
import com.zust.service.ElectricityDataService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Calendar;
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

	// 返回昨天24小时数据
	@GetMapping("getYesterdayData")
	public List<ElectricityData> getYesterdayEachHourData(Integer devId) {
		return electricityDataService.getAppointDayData(new Date(),devId);
	}

	// 返回当月用电量
	@GetMapping("getCurMonthC")
	public float getCurrentMonthConsumption() {
		return electricityDataService.getCurrentMonthConsumption();
	}
	//返回上月用电量
	@GetMapping("getLastMonthC")
	public float getLastMonthConsumption() {
		return electricityDataService.getLastMonthConsumption();
	}
	// 上面两个方法的结合
	@GetMapping("getMonthC")
	public String getMonthConsumption() {
		return electricityDataService.getCurrentMonthConsumption() + "," + electricityDataService.getLastMonthConsumption();
	}
	// 返回今日用电量和昨日用电量
	@GetMapping("getDayC")
	public String getDayConsumption() {
		return electricityDataService.getTodayConsumption() + "," + electricityDataService.getYesterdayConsumption();
	}
	// 返回一年12个月的用电量
	@GetMapping("getYearC")
	public float[] getYearConsumption() {
		return electricityDataService.getLastYearMonthConsumption();
	}
	// 返回指定日期一天的功率变化(24小时)
	@GetMapping("getDayTotalPower")
	public float[] getAppointedDayTotalPower() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = c.getTime();
		return electricityDataService.getAppointDayTotalPower(yesterday);
	}
}