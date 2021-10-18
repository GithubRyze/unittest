package com.ewell.hk;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableAsync
@Slf4j
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.ewell.hk.infrastructure.mapper"})
public class ESBHandlerApp {

  public static void main(String[] args) {
    SpringApplication.run(ESBHandlerApp.class, args);
  }

}


