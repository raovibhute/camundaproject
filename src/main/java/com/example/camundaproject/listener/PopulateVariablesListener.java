package com.example.camundaproject.listener;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

@Component("populateVariablesListener")
public class PopulateVariablesListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        System.out.println("start | execution listener");
        String itemCorrelationId = execution.getBusinessKey() + execution.getVariable("itemId");
        execution.setVariableLocal("localItemId", execution.getVariable("itemId"));
        System.out.println("end | execution listener");
    }
}
