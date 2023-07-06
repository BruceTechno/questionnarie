package com.example.questionnaire.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserAndQuestionJoinResponse {

    private String name;
    private String phone;
    private String mail;
    private int age;
    private int topicNumber;
    private String question ;
    private String answer;
    private LocalDate ansTime = LocalDate.now();
    private String options ;

    private int type;
    private boolean must;

//==

    public UserAndQuestionJoinResponse(String name, String phone, String mail, int age, int topicNumber, String question, String answer, LocalDate ansTime, String options, int type, boolean must) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.age = age;
        this.topicNumber = topicNumber;
        this.question = question;
        this.answer = answer;
        this.ansTime = ansTime;
        this.options = options;
        this.type = type;
        this.must = must;
    }

    public UserAndQuestionJoinResponse() {
    }

    //==


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public LocalDate getAnsTime() {
        return ansTime;
    }

    public void setAnsTime(LocalDate ansTime) {
        this.ansTime = ansTime;
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
