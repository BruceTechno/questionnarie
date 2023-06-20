package com.example.questionnaire.vo;

public class GetQuestionRequest {
    private int number;
    private String question;
//==


    public GetQuestionRequest() {
    }
//==

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
