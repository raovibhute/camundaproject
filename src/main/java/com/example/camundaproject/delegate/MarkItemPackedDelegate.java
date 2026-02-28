package com.example.camundaproject.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("markItemPackedDelegate")
public class MarkItemPackedDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {

    }
}
