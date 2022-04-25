package com.zy.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ZY
 * @create 2022/1/18 11:39
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan("com.zy")
@EnableDiscoveryClient
public class ossApplication {
    public static void main(String[] args) {
        SpringApplication.run(ossApplication.class,args);
    }
}
