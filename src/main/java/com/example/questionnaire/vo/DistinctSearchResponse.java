package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Topic;

import java.util.List;

public class DistinctSearchResponse {
    private List<Topic> topicList;
    private String message;
//==

    public DistinctSearchResponse() {
    }
//==

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
