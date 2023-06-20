package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Question;

public class UpdateQuestionResponse {
    private String message;
    private Question question;
//==

    public UpdateQuestionResponse() {
    }

    public UpdateQuestionResponse(String message) {
        this.message = message;
    }

    public UpdateQuestionResponse(String message, Question question) {
        this.message = message;
        this.question = question;
    }
//==

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
