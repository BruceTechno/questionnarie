package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Topic;

public class UpdateTopicResponse {
    private String message ;
    private Topic topic;
//==

    public UpdateTopicResponse() {
    }

    public UpdateTopicResponse(String message) {
        this.message = message;
    }

    public UpdateTopicResponse(String message, Topic topic) {
        this.message = message;
        this.topic = topic;
    }
//==

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
