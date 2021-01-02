package com.snnlab.singleF2Fjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SingleF2FJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(SingleF2FJobApplication.class, args);
    }

}
