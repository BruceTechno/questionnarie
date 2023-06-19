package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Topic;

public class AddTopicResponse {
    private String message;
    private Topic topic;
//==

    public AddTopicResponse(String message) {
        this.message = message;
    }

    public AddTopicResponse(String message, Topic topic) {
        this.message = message;
        this.topic = topic;
    }

    public AddTopicResponse() {
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
}
