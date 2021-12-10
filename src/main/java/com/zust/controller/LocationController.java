package com.zust.controller;

import com.zust.entity.Location;
import com.zust.service.LocationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Location)表控制层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@RestController
@RequestMapping("location")
public class LocationController {
  /**
   * 服务对象
   */
  @Resource
  private LocationService locationService;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public Location selectOne(Integer id) {
      return this.locationService.queryById(id);
  }

	@GetMapping("getAll")
	public List<Location> getAllLocation() {
		return locationService.getAllLocation();
	}
}