package com.example.questionnaire.vo;

import com.example.questionnaire.entity.Topic;

import java.util.List;

public class DistinctSearchTopicResponse {
    private String message;
    private List<DistinctSearchResult> distinctSearchResultList;
//==

    public DistinctSearchTopicResponse() {
    }

    public DistinctSearchTopicResponse(String message) {
        this.message = message;
    }

    public DistinctSearchTopicResponse(String message, List<DistinctSearchResult> distinctSearchResultList) {
        this.message = message;
        this.distinctSearchResultList = distinctSearchResultList;
    }



    //==

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DistinctSearchResult> getDistinctSearchResultList() {
        return distinctSearchResultList;
    }

    public void setDistinctSearchResultList(List<DistinctSearchResult> distinctSearchResultList) {
        this.distinctSearchResultList = distinctSearchResultList;
    }
}
