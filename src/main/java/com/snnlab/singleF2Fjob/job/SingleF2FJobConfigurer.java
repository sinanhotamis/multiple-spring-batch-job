package com.snnlab.singleF2Fjob.job;

import com.snnlab.singleF2Fjob.listener.BaseJobListener;
import com.snnlab.singleF2Fjob.model.SnnLabInfoDTO;
import com.snnlab.singleF2Fjob.step.chunk.SingleF2FJobItemReader;
import com.snnlab.singleF2Fjob.step.chunk.SingleF2FJobItemWriter;
import com.snnlab.singleF2Fjob.step.tasklet.SingleF2FJobTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingleF2FJobConfigurer extends BaseJobConfigurer {

    @Bean
    public Job singleF2FJob() {
        return jobBuilderFactory.get("singleF2FJob")
                .start(firstChunkOrientedStep())
                .next(taskletStep())
                .listener(new BaseJobListener())
                .build();
    }

    @Bean
    public Step firstChunkOrientedStep() {
         return  stepBuilderFactory.get("firstChunkOrientedStep")
                .<SnnLabInfoDTO, SnnLabInfoDTO>chunk(3)
                .reader(firstChunkOrientedStepReader())
                .writer(firstChunkOrientedStepWriter())
                .build();
    }

    @Bean
    public Step taskletStep() {
        return stepBuilderFactory.get("taskletStep")
                .tasklet(new SingleF2FJobTasklet())
                .build();
    }

    @Bean
    public ItemReader<SnnLabInfoDTO> firstChunkOrientedStepReader() {
        return new SingleF2FJobItemReader();
    }

    @Bean
    public ItemWriter firstChunkOrientedStepWriter() {
        return new SingleF2FJobItemWriter();
    }
}


