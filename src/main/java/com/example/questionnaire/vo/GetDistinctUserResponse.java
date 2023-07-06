package com.example.questionnaire.vo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GetDistinctUserResponse {
    private String name;
    private LocalDate localDate;
//==

    public GetDistinctUserResponse() {
    }

    public GetDistinctUserResponse(String name, LocalDate localDate) {
        this.name = name;
        this.localDate = localDate;
    }
    //==

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }
}
