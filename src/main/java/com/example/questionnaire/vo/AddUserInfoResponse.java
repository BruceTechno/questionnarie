package com.example.questionnaire.vo;

import com.example.questionnaire.entity.User;

public class AddUserInfoResponse {
    private String message;
    private User user;
//==

    public AddUserInfoResponse() {
    }

    public AddUserInfoResponse(String message) {
        this.message = message;
    }

    public AddUserInfoResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }
}
