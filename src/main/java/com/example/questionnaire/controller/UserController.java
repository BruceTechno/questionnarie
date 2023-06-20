package com.example.questionnaire.controller;

import com.example.questionnaire.service.ifs.UserService;
import com.example.questionnaire.vo.GetUsersAnswerRequest;
import com.example.questionnaire.vo.GetUsersAnswerResponse;
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
}
