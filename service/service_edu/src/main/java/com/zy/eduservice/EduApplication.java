package com.zy.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZY
 * @create 2022/1/3 15:38
 */
@SpringBootApplication
@ComponentScan("com.zy")
@EnableDiscoveryClient
@EnableFeignClients
//扫描@XXX的组件 包括其他模块下的
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class, args);
    }
}
