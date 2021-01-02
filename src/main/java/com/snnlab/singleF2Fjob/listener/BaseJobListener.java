package com.snnlab.singleF2Fjob.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobInstance;

import static org.springframework.batch.core.ExitStatus.COMPLETED;
import static org.springframework.batch.core.ExitStatus.FAILED;

public class BaseJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(generateGenericListenerMessageHeader(jobExecution.getJobInstance())
                .append(" has started...").toString());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        ExitStatus jobExecutionExitStatus = jobExecution.getExitStatus();
        if (COMPLETED.equals(jobExecutionExitStatus)) {
            System.out.println(generateGenericListenerMessageHeader(jobExecution.getJobInstance())
                    .append(" has completed..").toString());
        } else if (FAILED.equals(jobExecutionExitStatus)) {
            System.out.println(generateGenericListenerMessageHeader(jobExecution.getJobInstance())
                    .append(" has failed..").toString());
        }
    }

    private StringBuilder generateGenericListenerMessageHeader(JobInstance jobInstance){
        return new StringBuilder().append("Job id:")
                .append(jobInstance.getInstanceId())
                .append(" Job Name:")
                .append(jobInstance.getJobName());
    }
}
