package com.zust;

import com.zust.dao.ElectricityDataDao;
import com.zust.dao.LocationDao;
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
}
