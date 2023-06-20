package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.GetUsersAnswerRequest;
import com.example.questionnaire.vo.GetUsersAnswerResponse;

public interface UserService {
    public GetUsersAnswerResponse getUsersWhoAnswerThisTopic(GetUsersAnswerRequest request);


}
