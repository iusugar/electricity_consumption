package com.zust.controller;

import com.zust.entity.Room;
import com.zust.service.RoomService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * (Room)表控制层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@RestController
@RequestMapping("room")
public class RoomController {
    /**
     * 服务对象
     */
    @Resource
    private RoomService roomService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Room selectOne(Integer id) {
        return this.roomService.queryById(id);
    }

    @GetMapping("selectAllRoom")
    public List<Room> selectAllRoom() {
        return roomService.queryAllRoom();
    }

    @PostMapping("add")
    public String addNewRoom(@RequestBody ArrayList<String> roomArray) {
			if (Objects.equals(roomService.insert(roomArray), "success")) {
				return "success";
	    }
        return "failed";
    }
}