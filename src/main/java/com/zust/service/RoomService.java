package com.zust.service;

import com.zust.entity.Room;
import java.util.List;

/**
 * (Room)表服务接口
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface RoomService {

    String insert(List<String> l);

    /**
     * 查询全部数据
     *
     */
    List<Room> queryAllRoom();

    //
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Room queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Room> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    Room insert(Room room);

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 实例对象
     */
    Room update(Room room);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}