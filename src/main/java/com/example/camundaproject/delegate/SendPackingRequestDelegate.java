package com.example.camundaproject.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("sendPackingRequestDelegate")
public class SendPackingRequestDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("start | SendPackingRequestDelegate");
        System.out.println("itemId: " + execution.getVariable("itemId"));
        System.out.println("end | SendPackingRequestDelegate");
    }
}
