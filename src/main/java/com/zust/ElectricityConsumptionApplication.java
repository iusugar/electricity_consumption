package com.zust;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zust.dao")
public class ElectricityConsumptionApplication {

  public static void main(String[] args) {
    SpringApplication.run(ElectricityConsumptionApplication.class, args);
  }

}
