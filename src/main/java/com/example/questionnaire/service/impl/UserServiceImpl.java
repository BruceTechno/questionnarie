package com.example.questionnaire.service.impl;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Topic;
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

        return new GetUserInfoResponse(RtnCode.SUCCESSFUL.getMessage(), result);
    }

    @Override //判斷 狀態為開放才能 作答
    public AddUserInfoResponse addUserInfo(AddUserInfoRequest request) {//todo 0620 剩下addUserInfo & 模糊搜尋 & updateUserInfo
        // 抓使用者現在時間    2023-06-20T15:04:33.537196500 => 20230620
        String localDateTimeStr = LocalDateTime.now().toString().substring(0, 10).replace("-", "");
        int localDateTime = Integer.parseInt(localDateTimeStr);
        // Request
        String name = request.getName();
        String phone = request.getPhone();
        String mail = request.getMail();
        int age = request.getAge();
        int topicNumber = request.getTopicNumber();
        String question = request.getQuestion();
        String answer = request.getAnswer();
        LocalDateTime ansTime = request.getAnsTime();

         if (!StringUtils.hasText(name) || !StringUtils.hasText(phone) || !StringUtils.hasText(mail)
            || !StringUtils.hasText(question) || !StringUtils.hasText(answer)){
             return new AddUserInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
         }
        if (age <= 0 || topicNumber < 0 ){
            return new AddUserInfoResponse(RtnCode.DATA_ERROR.getMessage());
        }
        Topic topic = topicDao.findByNumber(topicNumber);
        if (topic == null) {
            return new AddUserInfoResponse(RtnCode.NOT_FOUND.getMessage()); //沒這份問卷拉
        }
        // 時間判斷 現在的時間 > 關閉時間 或 現在的時間 < 開始時間 ==>表示 不是關閉狀態也不是未開放狀態 =>即是 開放中
        if (localDateTime > topic.getEndTime() || localDateTime < topic.getStartTime()){
            return new AddUserInfoResponse(RtnCode.CAN_NOT_ANSWER_NOW.getMessage());
        }
        // ++JPQL
        return null;
    }
}
