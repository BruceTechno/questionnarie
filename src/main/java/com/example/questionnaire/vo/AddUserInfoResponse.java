package com.example.questionnaire.vo;

import com.example.questionnaire.entity.User;

import java.util.List;

public class AddUserInfoResponse {
    private String message;
    private List<User> userList;
//==

    public AddUserInfoResponse() {
    }

    public AddUserInfoResponse(String message) {
        this.message = message;
    }

    public AddUserInfoResponse(String message, List<User> userList) {
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
}
