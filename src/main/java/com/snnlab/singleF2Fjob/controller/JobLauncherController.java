package com.snnlab.singleF2Fjob.controller;

import com.snnlab.singleF2Fjob.job.JobExecuter;
import org.springframework.batch.core.JobParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class JobLauncherController extends BaseJobController {

    @Autowired
    private JobExecuter jobExecuter;

    @Override
    @RequestMapping("/jobLauncher")
    public void launch() throws Exception{
        jobExecuter.executeJobByDefaultParameter();
    }


    @Override
    @RequestMapping("/jobStopByExecutionId")
    public void stopByJobExecutionId(@RequestParam(name = "jobExecutionId") Long jobExecutionId) throws Exception{
        jobExecuter.stopJobByExecutionId(jobExecutionId);
    }
}
