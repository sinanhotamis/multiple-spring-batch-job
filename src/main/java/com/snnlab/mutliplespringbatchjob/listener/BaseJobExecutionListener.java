package com.snnlab.mutliplespringbatchjob.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import static org.springframework.batch.core.ExitStatus.*;

public class BaseJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println(generateGenericListenerMessageHeader(jobExecution)
                .append(" has started...").toString());
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        checkExitStatusAndLog(jobExecution);
    }

    private void checkExitStatusAndLog(JobExecution jobExecution) {
        ExitStatus jobExecutionExitStatus = jobExecution.getExitStatus();
        if (COMPLETED.equals(jobExecutionExitStatus)) {
            System.out.println(generateGenericListenerMessageHeader(jobExecution)
                    .append(" has completed..").toString());
        } else if (FAILED.equals(jobExecutionExitStatus)) {
            System.out.println(generateGenericListenerMessageHeader(jobExecution)
                    .append(" has failed..").toString());
            for(Throwable t : jobExecution.getAllFailureExceptions()){
                System.out.println("Exception:"+ t.getCause().getMessage());
            }
        }
        else if (NOOP.equals(jobExecutionExitStatus)) {
            System.out.println(generateGenericListenerMessageHeader(jobExecution)
                    .append(" has no operation..").toString());
        }
    }

    private StringBuilder generateGenericListenerMessageHeader(JobExecution jobExecution){
        return new StringBuilder()
                .append("Job Instance id:")
                .append(jobExecution.getJobInstance().getInstanceId())
                .append(" Job Name:")
                .append(jobExecution.getJobInstance().getJobName());
    }
}
