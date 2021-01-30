package com.snnlab.mutliplespringbatchjob.model.operator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOperatorResponse extends BaseBatchResponse {
    private Long newJobExecutionId;
    private Boolean isStop;
}
