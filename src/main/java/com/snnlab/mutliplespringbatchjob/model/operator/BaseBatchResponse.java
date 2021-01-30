package com.snnlab.mutliplespringbatchjob.model.operator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseBatchResponse implements Serializable {

    private String responseMessage;
}
