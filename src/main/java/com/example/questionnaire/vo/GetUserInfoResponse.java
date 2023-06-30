package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.User;

import java.util.List;

public class GetUserInfoResponse {
    private String message;
    private List<User> userList;
    private List<Question> questionList;
//==

    public GetUserInfoResponse() {
    }

    public GetUserInfoResponse(String message, List<User> userList, List<Question> questionList) {
        this.message = message;
        this.userList = userList;
        this.questionList = questionList;
    }

    public GetUserInfoResponse(String message) {
        this.message = message;
    }

    public GetUserInfoResponse(String message, List<User> userList) {
        this.message = message;
        this.userList = userList;
    }
//==

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }
}