package com.example.questionnaire.entity;


import javax.persistence.*;

@Entity
@Table(name = "question")

public class Question {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private boolean must;


//==


    public Question() {
    }

    public Question(String question, String options, int type, boolean must) {
        this.question = question;
        this.options = options;
        this.type = type;
        this.must = must;
    }

    public Question(int topicNumber, String question, String options, int type, boolean must) {
        this.topicNumber = topicNumber;
        this.question = question;
        this.options = options;
        this.type = type;
        this.must = must;
    }

    public Question(int id, int topicNumber, String question, String options, int type, boolean must) {
        this.id = id;
        this.topicNumber = topicNumber;
        this.question = question;
        this.options = options;
        this.type = type;
        this.must = must;
    }

    //==

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
        return must;
    }

    public void setMust(boolean must) {
        this.must = must;
    }
}
