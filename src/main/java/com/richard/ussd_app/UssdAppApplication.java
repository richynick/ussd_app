package com.richard.ussd_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class UssdAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UssdAppApplication.class, args);
    }

}
