package com.example.questionnaire.controller;

import com.example.questionnaire.service.ifs.UserService;
import com.example.questionnaire.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "get_users_who_answer_this_topic")
    public GetUsersAnswerResponse getUsersWhoAnswerThisTopic(@RequestBody GetUsersAnswerRequest request) {
        return userService.getUsersWhoAnswerThisTopic(request);
    }

    @PostMapping(value = "get_user_info")
    public GetUserInfoResponse getUserInfo(@RequestBody GetUserInfoRequest request) {
        return userService.getUserInfo(request);
    }

    @PostMapping(value = "add_user_info")
    public AddUserInfoResponse addUserInfo(@RequestBody AddUserInfoRequest request) {
        return userService.addUserInfo(request);
    }

    @PostMapping(value = "get_statistics")
    public StatisticsResponse getStatistics(@RequestBody StatisticsRequest request) {
        return userService.getStatistics(request);
    }

}
