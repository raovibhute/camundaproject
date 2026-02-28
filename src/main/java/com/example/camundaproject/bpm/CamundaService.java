package com.example.camundaproject.bpm;

import com.example.camundaproject.utils.OrderUtility;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component("camundaService")
public class CamundaService {

    private final RuntimeService runtimeService;
    private final OrderUtility orderUtility;

    public CamundaService(RuntimeService runtimeService, OrderUtility orderUtility) {
        this.runtimeService = runtimeService;
        this.orderUtility = orderUtility;
    }

    public void resumeProcess(String businessKey, String itemId, String jsonData) {
        List<Execution> executionList = runtimeService.createExecutionQuery().processInstanceBusinessKey(businessKey).list();
        String executionId = executionList.getFirst().getId();
        List<Map<String, Object>> processedItems = (ArrayList<Map<String, Object>>) runtimeService.getVariable(executionId, "processedItems");
        System.out.println("ExecutionId: " + executionList.getFirst().getId());
        System.out.println("Processed Items: " + processedItems);
        processedItems = orderUtility.processOrderData(processedItems, jsonData);
        runtimeService.createMessageCorrelation("MSG_ItemPacked")
                .processInstanceBusinessKey(businessKey)
                .localVariableEquals("localItemId", itemId)
                .setVariable("processedItems", processedItems)
                .correlate();
    }
}
