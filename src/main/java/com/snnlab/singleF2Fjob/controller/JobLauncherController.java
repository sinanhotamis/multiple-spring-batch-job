package com.snnlab.singleF2Fjob.controller;

import org.springframework.batch.core.JobParameters;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JobLauncherController extends BaseJobController {

    @RequestMapping("/jobLauncher.html")
    @Scheduled(cron = "${singleF2FJob.scheduler.cronExpression}")
    public void handle() throws Exception{
        jobLauncher.run(job, new JobParameters());
    }
}
