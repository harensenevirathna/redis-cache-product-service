package com.example.rediscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCrudApplication.class, args);
    }

}
