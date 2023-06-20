package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.GetUserInfoRequest;
import com.example.questionnaire.vo.GetUserInfoResponse;
import com.example.questionnaire.vo.GetUsersAnswerRequest;
import com.example.questionnaire.vo.GetUsersAnswerResponse;

public interface UserService {
    // For 問卷回饋
    public GetUsersAnswerResponse getUsersWhoAnswerThisTopic(GetUsersAnswerRequest request);
    // For 問卷回饋之內頁
    public GetUserInfoResponse getUserInfo (GetUserInfoRequest request);

}
