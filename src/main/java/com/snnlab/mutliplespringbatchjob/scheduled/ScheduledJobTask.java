package com.snnlab.mutliplespringbatchjob.scheduled;

import com.snnlab.mutliplespringbatchjob.job.JobNames;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJobTask extends BaseSecheduledJobTask {

    //@Scheduled(cron = "${scheduling.cronExpression.singleF2FJob}")
    public void executeSingleF2FJob() throws Exception {

        jobLauncher.run(jobRegistry.getJob(JobNames.SINGLE_F2F_JOB), new JobParametersBuilder()
                .addString("scheduledId", String.valueOf(System.currentTimeMillis()))
                .toJobParameters());
    }


    /*@Scheduled(cron = "${scheduling.cronExpression.singleF2FJob}")
    public void executeSingleD2FJob() throws Exception {

        jobLauncher.run(jobRegistry.getJob(JobNames.SINGLE_D2F_JOB), new JobParametersBuilder()
                .addString("scheduledId", String.valueOf(System.currentTimeMillis()))
                .toJobParameters());
    }*/

    //To be added other scheduled jobs..
}
