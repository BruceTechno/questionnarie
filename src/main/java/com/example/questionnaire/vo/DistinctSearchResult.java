package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Topic;

import java.util.List;

public class DistinctSearchResult {
    List<Topic> topicList;
//==

    public DistinctSearchResult() {
    }
//==

    public List<Topic> getTopicList() {
        return topicList;
    }

    public void setTopicList(List<Topic> topicList) {
        this.topicList = topicList;
    }
}
