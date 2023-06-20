package com.example.questionnaire.vo;

public class DeleteTopicResponse {
    private String message ;
//==

    public DeleteTopicResponse() {
    }

    public DeleteTopicResponse(String message) {
        this.message = message;
    }
    //==

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
