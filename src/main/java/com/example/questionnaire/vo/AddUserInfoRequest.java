package com.example.questionnaire.vo;

import java.time.LocalDateTime;

public class AddUserInfoRequest {
    private String name ;
    private String phone;
    private String mail;
    private int age;
    private int topicNumber;
    private String question;
    private String answer;
    private LocalDateTime ansTime ;
//==

    public AddUserInfoRequest() {
    }

    public AddUserInfoRequest(String name, String phone, String mail, int age, int topicNumber, String question, String answer, LocalDateTime ansTime) {
        this.name = name;
        this.phone = phone;
        this.mail = mail;
        this.age = age;
        this.topicNumber = topicNumber;
        this.question = question;
        this.answer = answer;
        this.ansTime = ansTime;
    }

    //==

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(int topicNumber) {
        this.topicNumber = topicNumber;
    }

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

    public LocalDateTime getAnsTime() {
        return ansTime;
    }

    public void setAnsTime(LocalDateTime ansTime) {
        this.ansTime = ansTime;
    }
}
