package com.snnlab.mutliplespringbatchjob.job.d2f;

import com.snnlab.mutliplespringbatchjob.job.BaseJobConfigurer;
import com.snnlab.mutliplespringbatchjob.job.JobNames;
import com.snnlab.mutliplespringbatchjob.listener.BaseJobExecutionListener;
import com.snnlab.mutliplespringbatchjob.model.SnnLabInfoDTO;
import com.snnlab.mutliplespringbatchjob.step.chunk.d2f.JdbcCursorItemReaderTemplate;
import com.snnlab.mutliplespringbatchjob.step.chunk.f2f.SingleF2FJobItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.net.MalformedURLException;

@Configuration
public class SingleD2FJobConfigurer extends BaseJobConfigurer {

    private DataSource dataSource;

    @Autowired
    public SingleD2FJobConfigurer(@Qualifier("secondarDataSource")DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public Job d2FJob() throws MalformedURLException {
        return jobBuilderFactory.get(JobNames.SINGLE_D2F_JOB)
                .start(firstDBChunkOrientedStep())
                .listener(new BaseJobExecutionListener())
                .build();
    }


    private Step firstDBChunkOrientedStep() throws MalformedURLException {
        return stepBuilderFactory.get("firstDBChunkOrientedStep")
                .<SnnLabInfoDTO, SnnLabInfoDTO>chunk(10)
                .reader(new JdbcCursorItemReaderTemplate(this.dataSource))
                .writer(new SingleF2FJobItemWriter())
                .build();
    }
}
