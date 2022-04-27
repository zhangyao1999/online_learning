package com.zy.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZY
 * @create 2022/4/26 15:48
 */
@SpringBootApplication
@ComponentScan({"com.zy"})
@MapperScan("com.zy.educms.mapper")
@EnableDiscoveryClient //nacos注册
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
