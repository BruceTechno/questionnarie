package com.example.questionnaire.service.impl;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.User;
import com.example.questionnaire.repository.TopicDao;
import com.example.questionnaire.repository.UserDao;
import com.example.questionnaire.service.ifs.UserService;
import com.example.questionnaire.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TopicDao topicDao;

    @Override
    public GetUsersAnswerResponse getUsersWhoAnswerThisTopic(GetUsersAnswerRequest request) {
        int number = request.getTopicNumber();

        if (number < 0) {
            return new GetUsersAnswerResponse(RtnCode.DATA_ERROR.getMessage());
        }
        if (topicDao.existsByNumber(number) == false) {
            return new GetUsersAnswerResponse(RtnCode.TOPIC_NOT_EXISTS.getMessage()); //壓根兒沒這問卷
        }
        List<GetDistinctUserResponse> result = userDao.getUsersWhoAnswerThisTopic(number);
        if (CollectionUtils.isEmpty(result)) {
            return new GetUsersAnswerResponse(RtnCode.NOT_FOUND.getMessage());//沒人回答過這問卷
        }

        return new GetUsersAnswerResponse(RtnCode.SUCCESSFUL.getMessage(), result);
    }

    @Override
    public GetUserInfoResponse getUserInfo(GetUserInfoRequest request) {
        String name = request.getName();
        LocalDateTime ansTime = request.getAnsTime();

        if (!StringUtils.hasText(name)) {
            return new GetUserInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        List<User> result = userDao.findByNameAndAnsTime(name, ansTime); //  問卷回饋之內頁 差一個option
        if (CollectionUtils.isEmpty(result)) {
            return new GetUserInfoResponse(RtnCode.NOT_FOUND.getMessage()); // 根本沒這個人
        }

        return new GetUserInfoResponse(RtnCode.SUCCESSFUL.getMessage(),result);
    }

}
