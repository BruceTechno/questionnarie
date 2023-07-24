package com.example.questionnaire.vo;

import java.util.List;

public class GetStatisticsRequest {
    private int topicNumber;
    private String question;
    private List<String> optionList;
//==

    public GetStatisticsRequest() {
    }
//==

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<String> optionList) {
        this.optionList = optionList;
    }
}
