package com.snnlab.singleF2Fjob.scheduled;

import com.snnlab.singleF2Fjob.job.JobExecuter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledJobTask {

    @Autowired
    private JobExecuter jobExecuter;

    @Scheduled(cron = "${singleF2FJob.scheduler.cronExpression}")
    public void execute() throws Exception
    {
        jobExecuter.executeJobByDefaultParameter();
    }
}
