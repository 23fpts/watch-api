package com.thc.watchapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.thc")
public class WatchapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WatchapiApplication.class, args);
    }

}
