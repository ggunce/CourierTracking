package com.migros.couriertrackingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableTransactionManagement
public class CourierTrackingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourierTrackingServiceApplication.class, args);
    }

}
