package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.*;

public interface UserService {
    // For 問卷回饋
    public GetUsersAnswerResponse getUsersWhoAnswerThisTopic(GetUsersAnswerRequest request);
    // For 問卷回饋之內頁
    public GetUserInfoResponse getUserInfo (GetUserInfoRequest request);
    // For 使用者作答
    public AddUserInfoResponse addUserInfo (AddUserInfoRequest request);

    public StatisticsResponse getStatistics (StatisticsRequest request);
    public StatisticsResponse getStatisticsForMultiple (StatisticsRequest request);
    public GetStatisticsResponse getAllStatistics (GetStatisticsRequest request);

}
