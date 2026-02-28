package com.example.camundaproject.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component("checkAllItemsPackedDelegate")
public class CheckAllItemsPackedDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Retrieve processed items
        List<Map<String, Object>> processedItems = (List<Map<String, Object>>) execution.getVariable("processedItems");
        // Check if all items have packed=true
        boolean allPacked = processedItems.stream()
                .allMatch(item -> Boolean.TRUE.equals(item.get("packed")));
        // Store result as process variable
        execution.setVariable("allItemsPacked", allPacked);
    }
}
