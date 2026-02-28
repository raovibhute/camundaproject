package com.example.camundaproject.controller;

import com.example.camundaproject.bpm.CamundaService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.Execution;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final RuntimeService runtimeService;
    private final CamundaService camundaService;

    public OrderController(RuntimeService runtimeService, CamundaService camundaService) {
        this.runtimeService = runtimeService;
        this.camundaService = camundaService;
    }

    @PostMapping("/{orderId}/items/{itemId}/packed")
    public ResponseEntity<String> markItemPacked(
            @PathVariable String orderId,
            @PathVariable String itemId,
            @RequestBody String updatedItemsJson) {
        System.out.println("order controller");
        System.out.println("updatedItemsJson: " + updatedItemsJson);
        camundaService.resumeProcess(orderId, itemId, updatedItemsJson);
        return ResponseEntity.ok("Item " + itemId + " packed for order " + orderId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<String> getExecutionData(@PathVariable String orderId) {
        List<Execution> list = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("MSG_ItemPacked")
                .processInstanceBusinessKey(orderId)
                .list();
        System.out.println("waiting executions: " + list.size());
        for (Execution execution: list) {
            System.out.println("ExecutionId: " + execution.getId());
            System.out.println("itemId: " + runtimeService.getVariableLocal(execution.getId(), "itemId"));
        }
        return ResponseEntity.ok("waiting executions: " + list.size());
    }

}