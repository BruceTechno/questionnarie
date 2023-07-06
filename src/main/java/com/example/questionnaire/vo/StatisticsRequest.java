package com.example.questionnaire.vo;

public class StatisticsRequest {
    private int number;
    private String answer;
    private String question;
//==

    public StatisticsRequest() {
    }
//==

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
