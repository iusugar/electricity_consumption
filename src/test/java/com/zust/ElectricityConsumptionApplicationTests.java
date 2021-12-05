package com.zust;

import com.zust.dao.*;
import com.zust.dto.DeviceDto;
import com.zust.dto.RoomDto;
import com.zust.entity.Device;
import com.zust.entity.DeviceStatus;
import com.zust.entity.ElectricityData;
import com.zust.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class ElectricityConsumptionApplicationTests {

	@Resource
	ElectricityDataDao ed;
	@Resource
	DeviceDao deviceDao;
	@Resource
	RoomDao roomDao;
	@Resource
	DeviceStatusDao dsDao;

  @Test
  void contextLoads() {
  }

	@Test
	void testQueryLocation(){
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -1);
		Date yesterday = c.getTime();
		String start = new SimpleDateFormat("yyyy-MM-dd 00:00:00").format(yesterday);
		String end = new SimpleDateFormat("yyyy-MM-dd 23:59:59").format(yesterday);
		List<ElectricityData> l = ed.queryByDate(start,end,57);
    System.out.println(l);
	}

	@Test
	void getAllDevice() {
		Device d = new Device();
		d.setId(40);
		List<Device> l = deviceDao.queryAll(d);
    System.out.println("L的长度" + l.size());
    System.out.println(l);
	}

	@Test
	void getRoomByOptions() {
//		List<DeviceDto> list = deviceDao.queryByOptions("实体","2f","A2");
//    list.forEach(System.out::println);
	}

	@Test
	void getStatus() {
		List<DeviceStatus> statusList = dsDao.queryHaveUsageHistory();
    statusList.forEach(System.out::println);
	}

	@Test
	void getMonthConsumption() {
    System.out.println(ed.lastMonthConsumptionByDevId(63));
	}

	@Test
	void getDayConsumption() {
//    System.out.println(ed.todayConsumptionById(63));
    System.out.println(ed.yesterdayConsumptionById(63));
	}
}
