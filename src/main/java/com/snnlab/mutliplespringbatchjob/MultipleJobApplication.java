package com.snnlab.mutliplespringbatchjob;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling
public class MultipleJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultipleJobApplication.class, args);
    }
}
