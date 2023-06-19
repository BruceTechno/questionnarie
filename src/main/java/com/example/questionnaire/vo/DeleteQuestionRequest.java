package com.example.questionnaire.vo;

public class DeleteQuestionRequest {
    private String question;
    private int number;
//==


    public DeleteQuestionRequest() {
    }
//==

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
