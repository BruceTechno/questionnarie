package com.example.questionnaire.vo;

import com.example.questionnaire.entity.User;

import java.util.List;

public class StatisticsResponse {
    private int result;
    private String message ;
//==


    public StatisticsResponse() {
    }

    public StatisticsResponse(int result, String message) {
        this.result = result;
        this.message = message;
    }

    public StatisticsResponse(String message) {
        this.message = message;
    }
//==

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
