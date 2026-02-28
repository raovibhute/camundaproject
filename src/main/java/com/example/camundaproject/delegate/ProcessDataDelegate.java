package com.example.camundaproject.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("processDataDelegate")
public class ProcessDataDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        List<String> items = new ArrayList<>();
        items.add("A123");
        items.add("B456");
        String correlationId = String.valueOf(UUID.randomUUID());
        execution.setVariable("correlationId", correlationId);
        execution.setVariable("items", items);
        List<Map<String, Object>> processedItems = new ArrayList<>();
        execution.setVariable("processedItems", processedItems);
    }
}
