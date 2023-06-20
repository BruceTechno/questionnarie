package com.example.questionnaire.vo;

import com.example.questionnaire.entity.User;

import java.util.List;

public class GetUsersAnswerResponse {
    private String message;
    private List<?> userList;
//==


    public GetUsersAnswerResponse() {
    }

    public GetUsersAnswerResponse(String message) {
        this.message = message;
    }

    public GetUsersAnswerResponse(String message, List<?> userList) {
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

    public List<?> getUserList() {
        return userList;
    }

    public void setUserList(List<?> userList) {
        this.userList = userList;
    }
}
