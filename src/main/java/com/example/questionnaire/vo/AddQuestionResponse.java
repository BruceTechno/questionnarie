package com.example.questionnaire.vo;

public class AddQuestionResponse {
    private String message;
//==

    public AddQuestionResponse() {
    }

    public AddQuestionResponse(String message) {
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
