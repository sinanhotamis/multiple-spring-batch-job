package com.snnlab.singleF2Fjob;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class SingleF2FJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleF2FJobApplication.class, args);
    }
}
