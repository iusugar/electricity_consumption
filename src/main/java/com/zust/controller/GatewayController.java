package com.zust.controller;

import com.zust.entity.Gateway;
import com.zust.service.GatewayService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Gateway)表控制层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@RestController
@RequestMapping("gateway")
public class GatewayController {
  /**
   * 服务对象
   */
  @Resource
  private GatewayService gatewayService;

  /**
   * 通过主键查询单条数据
   *
   * @param id 主键
   * @return 单条数据
   */
  @GetMapping("selectOne")
  public Gateway selectOne(Integer id) {
      return this.gatewayService.queryById(id);
  }

	@GetMapping("getAllGateway")
	public List<Gateway> getAllGateway() {
		return gatewayService.getAllGateway();
	}
}