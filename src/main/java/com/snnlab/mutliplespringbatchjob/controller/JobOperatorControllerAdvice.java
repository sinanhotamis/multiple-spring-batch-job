package com.snnlab.mutliplespringbatchjob.controller;

import com.snnlab.mutliplespringbatchjob.model.operator.BaseBatchResponse;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class JobOperatorControllerAdvice {

    @ExceptionHandler(JobExecutionException.class)
    public final ResponseEntity<BaseBatchResponse> jobExecutionException(JobExecutionException jobExecutionException) {
        BaseBatchResponse response = new BaseBatchResponse();
        response.setResponseMessage(jobExecutionException.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseBatchResponse> handleOtherExceptions(Exception exception) {
        BaseBatchResponse response = new BaseBatchResponse();
        response.setResponseMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
