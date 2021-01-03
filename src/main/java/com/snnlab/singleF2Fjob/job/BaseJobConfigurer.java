package com.snnlab.singleF2Fjob.job;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersIncrementer;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseJobConfigurer {

    @Autowired
    protected JobBuilderFactory jobBuilderFactory;

    @Autowired
    protected StepBuilderFactory stepBuilderFactory;

    protected JobParametersIncrementer jobIdIncrementer(){
        return jobParameters -> {
            if (jobParameters==null || jobParameters.isEmpty()) {
                return new JobParametersBuilder().addLong("run.id", 1L).toJobParameters();
            }
            long id = jobParameters.getLong("run.id",1L) + 1;
            return new JobParametersBuilder().addLong("run.id", id).toJobParameters();
        };
    }

}
