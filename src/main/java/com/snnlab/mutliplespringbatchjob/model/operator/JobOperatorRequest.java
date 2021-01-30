package com.snnlab.mutliplespringbatchjob.model.operator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOperatorRequest extends BaseBatchRequest {

    private Long jobExecutionId;
}
