package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Question;

public class GetQuestionResponse {
    private String message;
    private Question question;
//==

    public GetQuestionResponse() {
    }

    public GetQuestionResponse(String message) {
        this.message = message;
    }

    public GetQuestionResponse(String message, Question question) {
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
