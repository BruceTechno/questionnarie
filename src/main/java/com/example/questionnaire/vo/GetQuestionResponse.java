package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Question;

import java.util.List;

public class GetQuestionResponse {
    private String message;
    private List<Question> questionList;
//==

    public GetQuestionResponse() {
    }

    public GetQuestionResponse(String message) {
        this.message = message;
    }

    public GetQuestionResponse(String message, List<Question> questionList) {
        this.message = message;
        this.questionList = questionList;
    }

    //==

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }


}
