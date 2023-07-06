package com.example.questionnaire.vo;

public class UpdateQuestionRequest {
    private int number ;
    private String oldQuestion;
    private String options;
    private int type;
    private boolean must;
    private String newQuestion;

//==


    public UpdateQuestionRequest() {
    }
//==

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public boolean isMust() {
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getOldQuestion() {
        return oldQuestion;
    }

    public void setOldQuestion(String oldQuestion) {
        this.oldQuestion = oldQuestion;
    }

    public String getNewQuestion() {
        return newQuestion;
    }

    public void setNewQuestion(String newQuestion) {
        this.newQuestion = newQuestion;
    }
}
