package com.snnlab.mutliplespringbatchjob.controller;

import com.snnlab.mutliplespringbatchjob.model.operator.JobLauncherRequest;
import com.snnlab.mutliplespringbatchjob.model.operator.JobOperatorRequest;
import com.snnlab.mutliplespringbatchjob.model.operator.JobOperatorResponse;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public abstract class BaseJobController {

    @Autowired
    protected JobOperator jobOperator;

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected JobRegistry jobRegistry;

    @RequestMapping(value="/launch", method = RequestMethod.POST)
    public final ResponseEntity<String> launch(@RequestBody JobLauncherRequest jobLauncherRequest) throws Exception {
        JobParameters jobParameters = jobLauncherRequest.getJobParameters();
        if(null == jobParameters){
            jobParameters = new JobParameters();
        }
        jobLauncher.run(jobRegistry.getJob(jobLauncherRequest.getJobName()),jobParameters);
        return new ResponseEntity( "Job has launched successfully",HttpStatus.OK);
    }

    @RequestMapping(value="/stopJob",method = RequestMethod.POST)
    public final ResponseEntity<JobOperatorResponse> stopJob(@RequestBody JobOperatorRequest jobOperatorRequest) throws Exception{
        JobOperatorResponse response = new JobOperatorResponse();
        response.setIsStop(jobOperator.stop(jobOperatorRequest.getJobExecutionId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value="/restartJob",method = RequestMethod.POST)
    public final ResponseEntity<JobOperatorResponse> restartJob(@RequestBody JobOperatorRequest jobOperatorRequest) throws Exception {
        JobOperatorResponse response = new JobOperatorResponse();
        response.setNewJobExecutionId(jobOperator.restart(jobOperatorRequest.getJobExecutionId()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // You can add other job operator services as your needs..
}
