package com.ibond;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
//@EnableEurekaClient
@MapperScan(value = {"com.ibond.*.dao", "com.ibond.*.daoExtend"})
public class App {
    public static void main( String[] args )
    {
    	ApplicationContext app =SpringApplication.run(App.class, args);
    }
}
