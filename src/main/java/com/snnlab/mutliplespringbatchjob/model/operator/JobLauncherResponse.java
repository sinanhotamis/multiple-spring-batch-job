package com.snnlab.mutliplespringbatchjob.model.operator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.batch.core.JobExecution;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobLauncherResponse extends BaseBatchResponse {
    private JobExecution jobExecution;
}
