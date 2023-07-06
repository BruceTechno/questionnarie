package com.example.questionnaire.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GetUserInfoRequest {
    private String name;
    private LocalDate ansTime;
    private int topicNumber;
//==

    public GetUserInfoRequest() {
    }
//==

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getAnsTime() {
        return ansTime;
    }

    public void setAnsTime(LocalDate ansTime) {
        this.ansTime = ansTime;
    }
}
