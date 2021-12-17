package com.zust.service.impl;

import com.zust.dao.ElectricityDataDao;
import com.zust.dto.HistoricalDto;
import com.zust.entity.ElectricityData;
import com.zust.entity.HistoricalStatus;
import com.zust.dao.HistoricalStatusDao;
import com.zust.service.HistoricalStatusService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (HistoricalStatus)表服务实现类
 *
 * @author iusugar
 * @since 2021-12-07 09:33:43
 */
@Service("historicalStatusService")
public class HistoricalStatusServiceImpl implements HistoricalStatusService {
  @Resource
  private HistoricalStatusDao historicalStatusDao;
	@Resource
	private ElectricityDataDao electricityDataDao;

	/**
	 * 查找最近20条设备上下线记录
	 *
	 * @return 对象列表
	 */
	@Override
	public List<HistoricalDto> getRecentlyActivities() {
		return historicalStatusDao.queryRecentlyActivities();
	}

	/**
	 * 查询指定ID设备的上下线记录
	 *
	 * @param devId 设备主键ID
	 * @return 对象列表
	 */
	@Override
	public List<HistoricalStatus> getActivitiesHistoriesData(Integer devId) {
		return historicalStatusDao.queryActivitiesHistoriesDataByDevId(devId);
	}

	/**
	 * 查询一周每个小时在线设备数
	 *
	 * @return
	 */
	@Override
	public Integer[][] getWeekHourOnlineCount() {
		Integer[][] onlineData = new Integer[168][3];
		List<ElectricityData> eList = electricityDataDao.queryWeekHaveUsageHistory();
		List<Integer> idList = eList.stream().map(data -> data.getDevId()).collect(Collectors.toList());
//		for (int i = 0,sub = 0; i < 7; i++) {
//			for (int j = 0; j < 24; j++) {
//				onlineData[sub][0] = i;
//				onlineData[sub][1] = j;
//				sub++;
//			}
//		}
		// 计算今天是一周中的第几天
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int whichDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
		for (int i = 0,sub = 0; i < 7; i++) {
			for (int j = 0; j < 24; j++) {
				onlineData[sub][0] = i;
				onlineData[sub][1] = j;
				int total = 0;
				if (whichDay >= 0) {
					for (Integer id : idList) {
						HistoricalStatus hs = historicalStatusDao.queryWeekHourOnlineByDevId(id,whichDay,j,0);
						if (hs != null && (Boolean) hs.getStatus()) {
							total++;
						}
					}
				}
				onlineData[sub][2] = total;
				sub++;
			}
			whichDay--;
		}

		return onlineData;
	}

	// 数据不全 只能查找到每天在线的设备  弃用
	/**
	 * 查询一周每个小时在线的设备数
	 *
	 * @return 在线数据
	 */
	@Override
	public Integer[][] getWeekEachHourOnlineCount() {
		Integer[][] onlineData = new Integer[168][3];
		for (int i = 0,sub = 0; i < 7; i++) {
			for (int j = 0; j < 24; j++) {
				onlineData[sub][0] = i;
				onlineData[sub][1] = j;
				onlineData[sub][2] =historicalStatusDao.queryWeekEachHourOnlineCount(i,j,0);
				sub++;
			}
		}
		return onlineData;
	}

	/**
	 * 通过设备主键ID查询
	 *
	 * @param devId 设备主键ID
	 * @return 实例对象
	 */
	@Override
	public HistoricalStatus getByDevId(Integer devId) {
		return historicalStatusDao.queryByDevId(devId);
	}

	/**
   * 通过ID查询单条数据
   *
   * @param id 主键
   * @return 实例对象
   */
  @Override
  public HistoricalStatus queryById(Integer id) {
      return this.historicalStatusDao.queryById(id);
  }

  /**
   * 查询多条数据
   *
   * @param offset 查询起始位置
   * @param limit 查询条数
   * @return 对象列表
   */
  @Override
  public List<HistoricalStatus> queryAllByLimit(int offset, int limit) {
      return this.historicalStatusDao.queryAllByLimit(offset, limit);
  }

  /**
   * 新增数据
   *
   * @param historicalStatus 实例对象
   * @return 实例对象
   */
  @Override
  public HistoricalStatus insert(HistoricalStatus historicalStatus) {
      this.historicalStatusDao.insert(historicalStatus);
      return historicalStatus;
  }

  /**
   * 修改数据
   *
   * @param historicalStatus 实例对象
   * @return 实例对象
   */
  @Override
  public HistoricalStatus update(HistoricalStatus historicalStatus) {
      this.historicalStatusDao.update(historicalStatus);
      return this.queryById(historicalStatus.getId());
  }

  /**
   * 通过主键删除数据
   *
   * @param id 主键
   * @return 是否成功
   */
  @Override
  public boolean deleteById(Integer id) {
      return this.historicalStatusDao.deleteById(id) > 0;
  }
}