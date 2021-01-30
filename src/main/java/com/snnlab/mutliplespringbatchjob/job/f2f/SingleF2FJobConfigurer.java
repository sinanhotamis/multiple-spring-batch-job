package com.snnlab.mutliplespringbatchjob.job.f2f;

import com.snnlab.mutliplespringbatchjob.job.BaseJobConfigurer;
import com.snnlab.mutliplespringbatchjob.job.JobNames;
import com.snnlab.mutliplespringbatchjob.listener.BaseJobExecutionListener;
import com.snnlab.mutliplespringbatchjob.model.SnnLabInfoDTO;
import com.snnlab.mutliplespringbatchjob.step.chunk.f2f.SingleF2FJobItemReader;
import com.snnlab.mutliplespringbatchjob.step.chunk.f2f.SingleF2FJobItemWriter;
import com.snnlab.mutliplespringbatchjob.step.tasklet.f2f.SingleF2FJobTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SingleF2FJobConfigurer extends BaseJobConfigurer {

    @Bean
    public Job singleF2FJob() {
        return jobBuilderFactory.get(JobNames.SINGLE_F2F_JOB)
                .start(firstChunkOrientedStep())
                .next(taskletStep())
                .listener(new BaseJobExecutionListener())
                .build();
    }


    private Step firstChunkOrientedStep(){
        return  stepBuilderFactory.get("firstChunkOrientedStep")
                .<SnnLabInfoDTO, SnnLabInfoDTO>chunk(10)
                .reader(new SingleF2FJobItemReader())
                .writer(new SingleF2FJobItemWriter())
                .allowStartIfComplete(true)
                .build();
    }

    private Step taskletStep() {
        return stepBuilderFactory.get("taskletStep")
                .tasklet(new SingleF2FJobTasklet())
                .build();
    }
}


