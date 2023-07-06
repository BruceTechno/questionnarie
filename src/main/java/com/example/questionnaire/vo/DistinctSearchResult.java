package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Topic;

import javax.persistence.Column;
import java.util.List;

public class DistinctSearchResult {
    private int number;
    private String name;
    private int startTime;
    private int endTime;
    private String description;
//==

    public DistinctSearchResult() {
    }

    public DistinctSearchResult(int number ,String name, int startTime, int endTime, String description) {
        this.number= number;
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }
    //==


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
