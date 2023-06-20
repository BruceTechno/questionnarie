package com.example.questionnaire.vo;

import java.time.LocalDateTime;

public class GetUserInfoRequest {
    private String name;
    private LocalDateTime ansTime;
//==

    public GetUserInfoRequest() {
    }
//==

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getAnsTime() {
        return ansTime;
    }

    public void setAnsTime(LocalDateTime ansTime) {
        this.ansTime = ansTime;
    }
}
