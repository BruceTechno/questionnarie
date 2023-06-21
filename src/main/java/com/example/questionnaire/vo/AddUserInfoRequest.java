package com.example.questionnaire.vo;

import com.example.questionnaire.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class AddUserInfoRequest {
    private int number ;
    private List<User> userList;
//==

    public AddUserInfoRequest() {
    }

//==


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
