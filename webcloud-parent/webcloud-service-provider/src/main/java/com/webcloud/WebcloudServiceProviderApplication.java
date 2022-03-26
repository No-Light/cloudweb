package com.webcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cloud.dao")

public class WebcloudServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebcloudServiceProviderApplication.class, args);
    }

}
