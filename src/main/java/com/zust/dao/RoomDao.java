package com.zust.dao;

import com.zust.entity.Room;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * (Room)表数据库访问层
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
public interface RoomDao {



	/**
	 * 通过楼的编号查询单条数据
	 * @param buildingNum 楼的编号或门牌号
	 * @return Room对象
	 */
	Room queryByBuildingNum(@Param("buildingNum") String buildingNum);

    /**
     * 查询room表所有数据
     * @return 对象列表
     */
    List<Room> queryAllRoom();


    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Room queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Room> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param room 实例对象
     * @return 对象列表
     */
    List<Room> queryAll(Room room);

    /**
     * 新增数据
     *
     * @param room 实例对象
     * @return 影响行数
     */
    int insert(Room room);

    /**
     * 修改数据
     *
     * @param room 实例对象
     * @return 影响行数
     */
    int update(Room room);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}