package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Topic;

import java.util.List;

public class GetTopicInfoResponse {
    private String message;
    private List<Topic> topicList;
    private Topic topic;
//==


    public GetTopicInfoResponse(String message, Topic topic) {
        this.message = message;
        this.topic = topic;
    }

    public GetTopicInfoResponse() {
    }

    public GetTopicInfoResponse(String message) {
        this.message = message;
    }

    public GetTopicInfoResponse(String message, List<Topic> topicList) {
        this.message = message;
        this.topicList = topicList;
    }
//==

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }
}
