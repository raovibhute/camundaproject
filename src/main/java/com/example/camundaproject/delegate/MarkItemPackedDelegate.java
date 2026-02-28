package com.example.camundaproject.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.spin.Spin;
import org.camunda.spin.json.SpinJsonNode;
import org.springframework.stereotype.Component;

@Component("markItemPackedDelegate")
public class MarkItemPackedDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        /*String itemId = (String) execution.getVariable("itemId");
        SpinJsonNode items = Spin.JSON(execution.getVariable("items").toString());
        for (SpinJsonNode item : items.elements()) {
            if (item.prop("itemId").stringValue().equals(itemId)) {
                item.prop("packed", true);
            }
        }
        execution.setVariable("items", items.toString());*/
    }
}
