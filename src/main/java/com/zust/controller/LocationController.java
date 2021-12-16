package com.zust.controller;

import com.zust.entity.Location;
import com.zust.service.LocationService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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

	@GetMapping("getByRoom")
	public List<Location> getByRoomNum(String room) {
    System.out.println(room);
		return locationService.getByRoomNum(room);
	}

	@GetMapping("getLocationInfo")
	public Location getLocationInfo(String location,Integer id) {
		return locationService.queryById(id);
	}

	// 更新位置信息
	@PutMapping("updateLocationInfo")
	public String updateLocationInfo(@RequestBody(required = false) Location location) {
    System.out.println(location);
		Location l = locationService.queryById(location.getId());
		if (location.getDescription() == null || Objects.equals(location.getDescription(), "无")) {
      location.setDescription("");
		}
		if (Objects.equals(location.getPosition(), l.getPosition()) && Objects.equals(location.getDescription(), l.getDescription())) {
			return "exist";
		}else {
			locationService.update(location);
			return "success";
		}
	}

	// 删除一个位置
	@DeleteMapping("deleteLocation")
	public String deleteLocation(Integer id) {
    if (locationService.deleteById(id)) {
			return "success";
    }
		return "failed";
	}
}