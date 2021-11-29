package com.zust;

import com.zust.dao.DeviceDao;
import com.zust.dao.ElectricityDataDao;
import com.zust.dao.LocationDao;
import com.zust.dao.RoomDao;
import com.zust.dto.RoomDto;
import com.zust.entity.Device;
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
	void getRoomByDevIdList() {
//		List<RoomDto> rDto = roomDao.queryByDevIdList();
//    rDto.forEach(System.out::println);
	}
}
