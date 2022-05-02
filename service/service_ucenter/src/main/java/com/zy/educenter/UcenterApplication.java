package com.zy.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZY
 * @create 2022/4/27 15:34
 */
@SpringBootApplication
@ComponentScan({"com.zy"})
@MapperScan("com.zy.educenter.mapper")
@EnableDiscoveryClient //nacos注册
@EnableFeignClients
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class,args);
    }
}
