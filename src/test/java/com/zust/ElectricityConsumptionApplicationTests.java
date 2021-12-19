package com.zust;

import com.zust.dao.*;
import com.zust.dto.DeviceDto;
import com.zust.dto.ElectricityDto;
import com.zust.dto.RoomDto;
import com.zust.dto.StatusDto;
import com.zust.entity.*;
import com.zust.service.ElectricityDataService;
import com.zust.service.HistoricalStatusService;
import com.zust.service.LocationService;
import com.zust.service.RoomService;
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
	RoomService roomService;
	@Resource
	DeviceStatusDao dsDao;
	@Resource
	ElectricityDataService eds;
	@Resource
	HistoricalStatusDao hsd;
	@Resource
	HistoricalStatusService hsService;
	@Resource
	LocationDao locationDao;


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

	@Test
	void getYearConsumption() {
		List<ElectricityData> list1 = ed.lastYearMonthFirstConsumptionById(63);
    list1.forEach(System.out::println);
	}

	@Test
	void testGetYearConsumption() {
		eds.getLastYearMonthConsumption();
	}

	@Test
	void testYesterdayPower() {
		eds.getAppointDayTotalPower(new Date());
	}

	@Test
	void testLastUseTime() {
		List<StatusDto> l = dsDao.queryLastUseTime();
    l.forEach(System.out::println);
	}
	@Test
	void testGetWeekOnline() {
//    System.out.println(hsd.queryWeekEachHourOnlineNumber(1,14,0));
		hsService.getWeekEachHourOnlineCount();
	}
	@Test
	void testGetAllLocation() {
		List<Location> l = locationDao.queryAllLocation();
    l.forEach(System.out::println);
	}
	@Test
	void testInsertBuilding() {
		RoomDto roomDto = new RoomDto();
		roomDto.setCheckedBuilding("A55");
		roomDto.setBuildingDesc("测试楼号");
		roomService.insertBuilding(roomDto);
	}
	@Test
	void testGetWeekUseDevice() {
		List<ElectricityDto> l = eds.getWeekConsumedMostDevice();
    l.forEach(System.out::println);
	}
	@Test
	void testGetWeekEachDayConsumption() {
//    System.out.println(ed.weekEachDayConsumptionById(2, 63));
		List<ElectricityDto> l = eds.getWeekEachDayConsumption();
    l.forEach(System.out::println);
	}

	@Test
	void testGetHourOnline() {
//		hsService.getWeekHourOnlineCount();
//		Boolean b = hsd.queryWeekHourOnlineByDevId(88,3,16,0);
//    System.out.println(b);
//		deviceDao.queryByTimePoint(3,16);
	}
}
