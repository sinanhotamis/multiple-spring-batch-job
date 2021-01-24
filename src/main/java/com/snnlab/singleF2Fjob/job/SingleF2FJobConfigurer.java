package com.snnlab.singleF2Fjob.job;

import com.snnlab.singleF2Fjob.config.SingleF2FJobConfigInfo;
import com.snnlab.singleF2Fjob.listener.BaseJobListener;
import com.snnlab.singleF2Fjob.model.SnnLabInfoDTO;
import com.snnlab.singleF2Fjob.step.chunk.SingleF2FJobItemReader;
import com.snnlab.singleF2Fjob.step.chunk.SingleF2FJobItemWriter;
import com.snnlab.singleF2Fjob.step.tasklet.SingleF2FJobTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;

@Configuration
public class SingleF2FJobConfigurer extends BaseJobConfigurer {

    @Bean
    public Job singleF2FJob(@Autowired SingleF2FJobConfigInfo singleF2FJobConfigInfo) throws MalformedURLException {
        return jobBuilderFactory.get(singleF2FJobConfigInfo.getName())
                .start(firstChunkOrientedStep(singleF2FJobConfigInfo))
                .next(taskletStep())
                .listener(new BaseJobListener())
                .build();
    }


    private Step firstChunkOrientedStep(SingleF2FJobConfigInfo singleF2FJobConfigInfo) throws MalformedURLException {
        return  stepBuilderFactory.get("firstChunkOrientedStep")
                .<SnnLabInfoDTO, SnnLabInfoDTO>chunk(singleF2FJobConfigInfo.getChunkSize())
                .reader(new SingleF2FJobItemReader(singleF2FJobConfigInfo.getResourcePath()))
                .writer(new SingleF2FJobItemWriter(singleF2FJobConfigInfo.getResourcePath()))
                .build();
    }

    private Step taskletStep() {
        return stepBuilderFactory.get("taskletStep")
                .tasklet(new SingleF2FJobTasklet())
                .build();
    }
}


