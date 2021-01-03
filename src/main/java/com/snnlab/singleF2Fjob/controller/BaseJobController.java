package com.snnlab.singleF2Fjob.controller;

public abstract class BaseJobController {

    public abstract void launch() throws Exception;
    public abstract void stopByJobExecutionId(Long jobExecutionId) throws Exception;

}
