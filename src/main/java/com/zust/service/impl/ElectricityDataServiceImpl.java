package com.zust.service.impl;

import com.zust.entity.ElectricityData;
import com.zust.dao.ElectricityDataDao;
import com.zust.service.ElectricityDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * (ElectricityData)表服务实现类
 *
 * @author iusugar
 * @since 2021-11-18 16:01:42
 */
@Service("electricityDataService")
public class ElectricityDataServiceImpl implements ElectricityDataService {
    @Resource
    private ElectricityDataDao electricityDataDao;

	/**
	 * 通过日期和设备主键ID查询昨日用电数据
	 *
	 * @param date  日期
	 * @param devId 设备主键ID
	 * @return 实例对象集合
	 */
	@Override
	public List<ElectricityData> getAppointDayData(Date date, Integer devId) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = c.getTime();
		String start = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(yesterday);
		String end = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(yesterday);
		List<ElectricityData> l = electricityDataDao.queryByDate(start,end,devId);
		return l;
	}

	/**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ElectricityData queryById(Integer id) {
        return this.electricityDataDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ElectricityData> queryAllByLimit(int offset, int limit) {
        return this.electricityDataDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param electricityData 实例对象
     * @return 实例对象
     */
    @Override
    public ElectricityData insert(ElectricityData electricityData) {
        this.electricityDataDao.insert(electricityData);
        return electricityData;
    }

    /**
     * 修改数据
     *
     * @param electricityData 实例对象
     * @return 实例对象
     */
    @Override
    public ElectricityData update(ElectricityData electricityData) {
        this.electricityDataDao.update(electricityData);
        return this.queryById(electricityData.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.electricityDataDao.deleteById(id) > 0;
    }
}