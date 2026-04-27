package com.junie.juojbackenduserservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.junie.juojbackenduserservice.mapper")
@EnableScheduling
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
@ComponentScan("com.junie")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.junie.juojbackendserviceclient.service"})
public class JuojBackendUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JuojBackendUserServiceApplication.class, args);
    }

}
