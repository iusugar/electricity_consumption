package com.zust.service.impl;

import com.zust.dao.DeviceStatusDao;
import com.zust.entity.DeviceStatus;
import com.zust.entity.ElectricityData;
import com.zust.dao.ElectricityDataDao;
import com.zust.service.ElectricityDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		@Resource
		private DeviceStatusDao deviceStatusDao;


	/**
	 * 获取指定日期一天总用电功率(24小时)
	 *
	 * @return 24小时的用电功率
	 */
	@Override
	public float[] getAppointDayTotalPower(Date date) {
		float[] totalPower = new float[24];
		List<DeviceStatus> statusList = deviceStatusDao.queryHaveUsageHistory();
		if (statusList == null || statusList.size() < 1) {
			return null;
		}
		String start = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(date);
		String end = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(date);
		List<ElectricityData> eList = new ArrayList<>();
    for (DeviceStatus status : statusList) {
	    eList = electricityDataDao.queryByDate(start,end,status.getDevId());
      if (eList == null || eList.size() < 1) {
				continue;
      }
			for (int i = 0,j = 0; i < 24 && j < eList.size(); i++) {
				int hour = Integer.parseInt(new SimpleDateFormat("HH").format(eList.get(j).getCreateTime()));
				if (hour == i+1) {
					totalPower[i] += Float.parseFloat(eList.get(j).getInsPower().toString());
					j++;
				}
			}
    }
		return totalPower;
	}

	/**
	 * 获取去年每个月的用电量
	 *
	 * @return 12个月的用电数据
	 */
	@Override
	public float[] getLastYearMonthConsumption() {
		float[] monthConsumption = new float[12];
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		List<DeviceStatus> statusList = deviceStatusDao.queryHaveUsageHistory();
		if (statusList == null || statusList.size() < 1) {
			return null;
		}
    for (DeviceStatus deviceStatus : statusList) {
	    List<ElectricityData> firstDataList = electricityDataDao.lastYearMonthFirstConsumptionById(deviceStatus.getDevId());
			List<ElectricityData> lastDataList = electricityDataDao.lastYearMonthLastConsumptionById(deviceStatus.getDevId());
			if (firstDataList == null || firstDataList.size() < 1) {
				continue;
			}
      System.out.println(firstDataList);
	    float[] firstConsumption = new float[12];
	    float[] LastConsumption = new float[12];
			for (ElectricityData ed : firstDataList) {
				int index = Integer.parseInt(dateFormat.format(ed.getCreateTime())) - 1;
				firstConsumption[index] = Float.parseFloat(ed.getConsumption().toString());
			}
      for (ElectricityData ed : lastDataList) {
	      int index = Integer.parseInt(dateFormat.format(ed.getCreateTime())) - 1;
	      LastConsumption[index] = Float.parseFloat(ed.getConsumption().toString());
      }
      for (int i = 0; i< 12; i++) {
	      monthConsumption[i] += LastConsumption[i] - firstConsumption[i];
      }
    }
		for (float v : monthConsumption) {
			System.out.println(v);
		}

		return monthConsumption;
	}

	/**
	 * 获取昨日用电量
	 *
	 * @return 用电量
	 */
	@Override
	public float getYesterdayConsumption() {
		float total = 0;
		List<DeviceStatus> statusList = deviceStatusDao.queryHaveUsageHistory();
		if (statusList == null) {
			return 0;
		}
		for (DeviceStatus ds : statusList) {
			Object o = electricityDataDao.yesterdayConsumptionById(ds.getDevId());
			if (o != null) {
				total += Float.parseFloat(o.toString());
			}
		}
		return total;
	}

	/**
	 * 获取今日用电量
	 *
	 * @return 用电量
	 */
	@Override
	public float getTodayConsumption() {
		float total = 0;
		List<DeviceStatus> statusList = deviceStatusDao.queryHaveUsageHistory();
		if (statusList == null) {
			return 0;
		}
		for (DeviceStatus ds : statusList) {
			Object o = electricityDataDao.todayConsumptionById(ds.getDevId());
			if (o != null) {
				total += Float.parseFloat(o.toString());
			}
		}
		return total;
	}

	/**
	 * 获取上个月用电量
	 *
	 * @return 用电量
	 */
	@Override
	public float getLastMonthConsumption() {
		float total = 0;
		List<DeviceStatus> statusList = deviceStatusDao.queryHaveUsageHistory();
		if (statusList == null) {
			return 0;
		}
		for (DeviceStatus ds : statusList) {
			Object o = electricityDataDao.lastMonthConsumptionByDevId(ds.getDevId());
			if (o != null) {
				total += Float.parseFloat(o.toString());
			}
		}
		return total;
	}

	/**
	 * 获取当前月份用电量
	 *
	 * @return 用电量
	 */
	@Override
	public float getCurrentMonthConsumption() {
		float total = 0;
		List<DeviceStatus> statusList = deviceStatusDao.queryHaveUsageHistory();
		if (statusList == null) {
			return 0;
		}
    for (DeviceStatus ds : statusList) {
			Object o = electricityDataDao.currentMonthConsumptionByDevId(ds.getDevId());
			if (o != null) {
				total += Float.parseFloat(o.toString());
			}
    }
		return total;
	}

	/**
	 * 通过日期和设备主键ID查询昨日24时用电数据
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
		return electricityDataDao.queryByDate(start,end,devId);
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