package com.zust.controller;

import com.zust.dto.HistoricalDto;
import com.zust.entity.HistoricalStatus;
import com.zust.service.HistoricalStatusService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (HistoricalStatus)表控制层
 *
 * @author iusugar
 * @since 2021-12-07 09:33:43
 */
@RestController
@RequestMapping("historicalStatus")
public class HistoricalStatusController {
  /**
   * 服务对象
   */
  @Resource
  private HistoricalStatusService historicalStatusService;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public HistoricalStatus selectOne(Integer id) {
      return this.historicalStatusService.queryById(id);
  }

	// 返回一周每天每个小时在线设备数量
	@GetMapping("getOnlineDeviceCount")
	public Integer[][] getWeekEachHourOnlineCount() {
		return historicalStatusService.getWeekHourOnlineCount();
	}

	// 返回设备上下线记录
	@GetMapping("deviceActivitiesData")
	public List<HistoricalStatus> getDeviceActivitiesHistoricalData(Integer id) {
		return historicalStatusService.getActivitiesHistoriesData(id);
	}

	// 返回最近设备上下线记录
	@GetMapping("recentlyActivitiesData")
	public List<HistoricalDto> getRecentlyActivitiesData() {
		return historicalStatusService.getRecentlyActivities();
	}
}