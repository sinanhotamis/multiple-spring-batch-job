package com.snnlab.mutliplespringbatchjob.model.operator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.JobParameters;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobLauncherRequest extends BaseBatchRequest {

    private String jobName;
    private JobParameters jobParameters;
}
