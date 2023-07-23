package com.example.questionnaire.service.impl;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Topic;
import com.example.questionnaire.entity.User;
import com.example.questionnaire.repository.QuestionDao;
import com.example.questionnaire.repository.TopicDao;
import com.example.questionnaire.repository.UserDao;
import com.example.questionnaire.service.ifs.UserService;
import com.example.questionnaire.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private QuestionDao questionDao;

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

    @Override  //問題表的資訊也要抓出來
    public GetUserInfoResponse getUserInfo(GetUserInfoRequest request) {
        String name = request.getName();
        LocalDate ansTime = request.getAnsTime();
        int number = request.getTopicNumber();
        if (number < 0 ){
            return new GetUserInfoResponse(RtnCode.DATA_ERROR.getMessage());
        }
        if (!StringUtils.hasText(name)) {
            return new GetUserInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        List<User> userResult = userDao.findByNameAndAnsTimeAndTopicNumber(name, ansTime,number); //  問卷回饋之內頁 差一個option
        if (CollectionUtils.isEmpty(userResult)) {
            return new GetUserInfoResponse(RtnCode.NOT_FOUND.getMessage()); // 根本沒這個人
        }
        List<Question> questionResult = questionDao.findAllByTopicNumber(number);
        if (CollectionUtils.isEmpty(questionResult)){
            return new GetUserInfoResponse(RtnCode.NOT_FOUND.getMessage());
        }

        return new GetUserInfoResponse(RtnCode.SUCCESSFUL.getMessage(), userResult,questionResult);
    }

    //todo 0620 剩下addUserInfo DONE & 模糊搜尋(LEFT) & (updateUserInfo 這個寫在前端 直接對存在Session 裡面的資料作修改)
    @Override //判斷 狀態為開放才能 作答
    public AddUserInfoResponse addUserInfo(AddUserInfoRequest request) {

        // 抓使用者現在時間    2023-06-20T15:04:33.537196500 => 20230620
        String localDateTimeStr = LocalDateTime.now().toString().substring(0, 10).replace("-", "");
        int localDateTime = Integer.parseInt(localDateTimeStr);
        // Request
        int number = request.getNumber();
        List<User> userList = request.getUserList();
        if (CollectionUtils.isEmpty(userList)){
            return new AddUserInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        Topic topic = topicDao.findByNumber(number);
        if (topic == null){
            return  new AddUserInfoResponse(RtnCode.NOT_FOUND.getMessage());//沒這份問卷
        }
        // PostMan List 帶 name phone mail age  question answer
        // topicNumber 用 set , ansTime 也用set
        for (User item :userList){
            if (localDateTime > topic.getEndTime() || localDateTime <topic.getStartTime()){
                return new AddUserInfoResponse(RtnCode.CAN_NOT_ANSWER_NOW.getMessage());
            }
            if (!StringUtils.hasText(item.getName()) || !StringUtils.hasText(item.getPhone()) || !StringUtils.hasText(item.getMail())
                 || !StringUtils.hasText(item.getQuestion()) || !StringUtils.hasText(item.getAnswer())){
                 return new AddUserInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
                 }
            if(item.getAge() < 0){
                return new AddUserInfoResponse(RtnCode.DATA_ERROR.getMessage());
            }
            item.setAnsTime(LocalDate.now());
            item.setTopicNumber(number);
            if (userDao.existsByMailAndQuestion(item.getMail(), item.getQuestion())){
                return new AddUserInfoResponse(RtnCode.DATA_DUPLICATE.getMessage());// 填寫資料重複
                //但我覺得不需要判斷這個 因為在前端 題目跟選項都是已經固定渲染好上去的 不會發生重複填寫資料的狀況????
            }
        }
        userDao.saveAll(userList);
        return new AddUserInfoResponse(RtnCode.SUCCESSFUL.getMessage(),userList);
    }

    @Override
    public StatisticsResponse getStatistics(StatisticsRequest request) {
        int number = request.getNumber();
        String answer = request.getAnswer();
        String question = request.getQuestion();
        if (number < 0 ){
            return new StatisticsResponse(RtnCode.DATA_ERROR.getMessage());
        }
        if (!StringUtils.hasText(answer) || !StringUtils.hasText(question)){
            return new StatisticsResponse(RtnCode.DATA_ERROR.getMessage());
        }
        int result = userDao.getStatisticsByTopicNumberAndAnswer(number, answer,question);

        return new StatisticsResponse(result,RtnCode.SUCCESSFUL.getMessage());
    }
}
//    String name = request.getName();
//    String phone = request.getPhone();
//    String mail = request.getMail();
//    int age = request.getAge();
//    int topicNumber = request.getTopicNumber();
//    List<String> questionList = request.getQuestion();
//    List<String> answerList = request.getAnswer();
//    LocalDateTime ansTime = request.getAnsTime();
//
//         if (!StringUtils.hasText(name) || !StringUtils.hasText(phone) || !StringUtils.hasText(mail)
//                 || CollectionUtils.isEmpty(questionList) || CollectionUtils.isEmpty(answerList)){
//                 return new AddUserInfoResponse(RtnCode.CANNOT_EMPTY.getMessage());
//                 }
//
//                 if (age <= 0 || topicNumber < 0 ){
//        return new AddUserInfoResponse(RtnCode.DATA_ERROR.getMessage());
//        }
//        Topic topic = topicDao.findByNumber(topicNumber);
//        if (topic == null) {
//        return new AddUserInfoResponse(RtnCode.NOT_FOUND.getMessage()); //沒這份問卷拉
//        }
//        // 時間判斷 現在的時間 > 關閉時間 或 現在的時間 < 開始時間 ==>表示 不是關閉狀態也不是未開放狀態 =>即是 開放中
//        if (localDateTime > topic.getEndTime() || localDateTime < topic.getStartTime()){
//        return new AddUserInfoResponse(RtnCode.CAN_NOT_ANSWER_NOW.getMessage());
//        }
//// 問題與答案 要是list
//