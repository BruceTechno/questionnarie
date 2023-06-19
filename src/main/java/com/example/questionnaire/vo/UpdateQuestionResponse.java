package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Question;

public class UpdateQuestionResponse {
    private String message;
    private Question question;
//==

    public UpdateQuestionResponse() {
    }

    public UpdateQuestionResponse(String message, Question question) {
        this.message = message;
        this.question = question;
    }
}
