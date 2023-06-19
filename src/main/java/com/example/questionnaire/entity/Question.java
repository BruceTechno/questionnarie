package com.example.questionnaire.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "topic_number")
    private int topicNumber;
    @Column(name = "question")
    private String question;
    @Column(name = "options")
    private String options;
    @Column(name = "type")
    private int type;
    @Column(name = "must")
    private boolean isMust;

//==


    public Question() {
    }

    public Question(int topicNumber, String question, String options, int type, boolean isMust) {
        this.topicNumber = topicNumber;
        this.question = question;
        this.options = options;
        this.type = type;
        this.isMust = isMust;
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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isMust() {
        return isMust;
    }

    public void setMust(boolean must) {
        isMust = must;
    }


}
