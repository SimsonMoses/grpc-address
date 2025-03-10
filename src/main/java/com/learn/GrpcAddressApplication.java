package com.learn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class GrpcAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcAddressApplication.class, args);
    }

}
