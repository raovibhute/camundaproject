package com.example.camundaproject.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("orderUtility")
public class OrderUtility {

    public List<Map<String, Object>> processOrderData(List<Map<String, Object>> processedItems, String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Map<String, Object>> data = mapper.readValue(json, new TypeReference<List<Map<String, Object>>>() {});
            if (processedItems == null || processedItems.isEmpty()) {
                return data;
            }
            Map<String, Map<String, Object>> map = new HashMap<>();
            for (Map<String, Object> processedData: processedItems) {
                map.put((String) processedData.get("itemId"), processedData);
            }
            for(Map<String, Object> mapData: data) {
                map.put((String) mapData.get("itemId"), mapData);
            }
            return new ArrayList<>(map.values());
        } catch (Exception e) {
            throw new RuntimeException("Invalid JSON string: " + json, e);
        }

    }
}
