package com.snnlab.singleF2Fjob.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseJobController {

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected Job job;
}
