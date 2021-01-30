package com.snnlab.mutliplespringbatchjob.scheduled;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseSecheduledJobTask {

    @Autowired
    protected JobLauncher jobLauncher;

    @Autowired
    protected JobRegistry jobRegistry;
}
