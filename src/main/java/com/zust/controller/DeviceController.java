package com.zust.controller;

import com.zust.dto.DeviceDto;
import com.zust.entity.Device;
import com.zust.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* (Device)表控制层
*
* @author iusugar
* @since 2021-11-18 16:01:42
*/
@RestController
@RequestMapping("device")
public class DeviceController {
  /**
   * 服务对象
   */
  @Resource
  private DeviceService deviceService;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public Device selectOne(Integer id) {
      return this.deviceService.queryById(id);
  }

  @PostMapping(value = "add")
  public String addDevice(@RequestBody(required = false) DeviceDto deviceDto) {
		if (deviceService.queryByDeviceId(deviceDto.getDeviceId()) != null) {
			return "exist";
		}
	  deviceService.insert(deviceDto);
		return "success";
  }

	// 添加设备前先查询是否存在
	@GetMapping("isExist")
	public String checkDeviceIsExist(@RequestParam(value = "deviceId",required = false) String deviceId) {
		if (deviceService.queryByDeviceId(deviceId) != null) {
			return "exist";
		}
		return null;
	}

	// 返回该房间中的设备
	@GetMapping("getRoomDevice")
	public List<DeviceDto> getDeviceByRoom(String room) {
		return deviceService.queryByRoomNum(room);
	}

	@GetMapping("getAllDevice")
	public List<DeviceDto> getAllDevice() {
		return deviceService.getAllDevice();
	}

	@GetMapping("getDeviceList")
	public List<DeviceDto> getDeviceList(DeviceDto dto) {
		return deviceService.getByOptions(dto.getUsageDesc(),dto.getDeviceId(),dto.getBuildNum(),dto.getRoomNum());
	}

	// 更新设备数据
	@PutMapping("update")
	public String updateDevice(@RequestBody(required = false)DeviceDto deviceDto) {
		return deviceService.updateDevice(deviceDto);
	}

	// 删除设备
	@DeleteMapping("delete")
	public String deleteDevice(@RequestBody(required = false) String deviceId) {
		return deviceService.deleteByDeviceId(deviceId);
	}

	@GetMapping("getOnlineDevice")
	public List<Device> getTimePointOnlineDevice(Integer day, Integer hour) {
    System.out.println(day + ":" + hour);
		return deviceService.getOnlineDeviceByTimePoint(day,hour);
	}
	// 返回指定网关下的设备
	@GetMapping("getByGateway")
	public List<Device> getDeviceByGateway(String gateway) {
    System.out.println(gateway);
		return deviceService.getDeviceByGateway(gateway);
	}
}