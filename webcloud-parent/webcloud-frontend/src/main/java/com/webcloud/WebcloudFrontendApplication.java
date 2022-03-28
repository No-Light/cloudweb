package com.webcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan("com.webcloud.action")
public class WebcloudFrontendApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebcloudFrontendApplication.class, args);
    }

}
