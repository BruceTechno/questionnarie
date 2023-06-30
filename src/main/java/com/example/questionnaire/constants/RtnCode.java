package com.example.questionnaire.constants;


public enum RtnCode {

    SUCCESSFUL("200","Successful!!"),
    CANNOT_EMPTY("400","Data is empty!!!"),
    CONTENT_EMPTY("400", "The commoditys list is Empty!!"),

    NUMBER_ERROR("400", "Commodity Number is Overlap!!"),
    DATA_ERROR("400","Data is error!!!"),
    DATA_DUPLICATE("400","Data is duplicate"),
    NOT_FOUND("404","Not found!!!"),
// For Questionnaire
    TIME_ERROR("400","End time earlier than Start time"),
    NOT_OPEN_YET("400","Questionnaire not open yet"),
    ALREADY_CLOSER("400","Questionnaire already close"),
    CAN_NOT_UPDATE("400","Can not update now"),
    TOPIC_NOT_EXISTS("400","This topic not exists"),

    CAN_NOT_DELETE("400","Topic Can not Delete !"),
    START_TIME_ERROR("400","New start time can't earlier than the original one"),
    UPDATE_FAILED("400","Update failed"),
    CAN_NOT_ANSWER_NOW("400","Can not answer now"),

    CAN_NOT_ADD_NOW("400","The start time already passed");

    private String code;
    private String message;
//=======================================================================================

    RtnCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    RtnCode() {
    }

    //========================================================================================

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
