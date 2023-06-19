package com.example.questionnaire.vo;

public class DeleteQuestionResponse {
    private String message ;
//==

    public DeleteQuestionResponse() {
    }

    public DeleteQuestionResponse(String message) {
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
