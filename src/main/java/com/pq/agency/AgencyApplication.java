package com.pq.agency;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@EnableHystrix
@MapperScan("com.pq.agency.mapper")
@EnableEurekaClient
@SpringBootApplication
public class AgencyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(AgencyApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(AgencyApplication.class);
    }
}
