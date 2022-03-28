package com.webcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication
@ServletComponentScan("com.webcloud.action")
public class WebcloudBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebcloudBackendApplication.class, args);
    }

}
