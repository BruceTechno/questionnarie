package com.example.questionnaire.vo;

import java.util.Map;

public class GetStatisticsResponse {
    private String message;
    private Map<String,Integer> optionAndValues;
//==

    public GetStatisticsResponse() {
    }
//==

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Integer> getOptionAndValues() {
        return optionAndValues;
    }

    public void setOptionAndValues(Map<String, Integer> optionAndValues) {
        this.optionAndValues = optionAndValues;
    }
}
