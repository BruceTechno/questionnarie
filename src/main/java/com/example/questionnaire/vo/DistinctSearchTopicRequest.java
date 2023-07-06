package com.example.questionnaire.vo;

public class DistinctSearchTopicRequest {
    private String topicName;
    private int startTime;
    private int endTime;
//==

    public DistinctSearchTopicRequest() {
    }
//==

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
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
}
