package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Question;

import java.util.List;

public class AddQuestionRequest {

    private int number;
//    private String question;
//    private String options;
//    private  int type;
//    private boolean must;
//     存在時間會存在session裡面再拿出來
//    private int endTime;
    private List<Question> questionList;

//==
    public AddQuestionRequest() {
    }
//==


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}
