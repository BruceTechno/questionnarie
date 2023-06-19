package com.example.questionnaire.vo;

public class AddTopicRequest {
    private String name;
    private String startY;
    private String startM;
    private String startD;
    private String endY;
    private String endM;
    private String endD;
    private String description;
//==

    public AddTopicRequest() {
    }
//==


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartY() {
        return startY;
    }

    public void setStartY(String startY) {
        this.startY = startY;
    }

    public String getStartM() {
        return startM;
    }

    public void setStartM(String startM) {
        this.startM = startM;
    }

    public String getStartD() {
        return startD;
    }

    public void setStartD(String startD) {
        this.startD = startD;
    }

    public String getEndY() {
        return endY;
    }

    public void setEndY(String endY) {
        this.endY = endY;
    }

    public String getEndM() {
        return endM;
    }

    public void setEndM(String endM) {
        this.endM = endM;
    }

    public String getEndD() {
        return endD;
    }

    public void setEndD(String endD) {
        this.endD = endD;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
