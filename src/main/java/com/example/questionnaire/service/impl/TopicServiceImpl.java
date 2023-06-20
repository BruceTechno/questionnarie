package com.example.questionnaire.service.impl;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Topic;
import com.example.questionnaire.repository.QuestionDao;
import com.example.questionnaire.repository.TopicDao;
import com.example.questionnaire.service.ifs.TopicService;
import com.example.questionnaire.vo.AddTopicRequest;
import com.example.questionnaire.vo.AddTopicResponse;
import com.example.questionnaire.vo.DeleteTopicRequest;
import com.example.questionnaire.vo.DeleteTopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;
    @Autowired
    private QuestionDao questionDao;

    @Override
    public AddTopicResponse addTopic(AddTopicRequest request) {
        // 新增問卷時 按下下一步 製造出亂數 當作topic number
        int number = request.getNumber();

        String name = request.getName();
        String description = request.getDescription();
        String startY = request.getStartY();
        String startM = request.getStartM();
        String startD = request.getStartD();
        String endY = request.getEndY();
        String endM = request.getEndM();
        String endD = request.getEndD();


        if (!StringUtils.hasText(name) || !StringUtils.hasText(description)
                || !StringUtils.hasText(startY) || !StringUtils.hasText(startM) || !StringUtils.hasText(startD)
                || !StringUtils.hasText(endY) || !StringUtils.hasText(endM) || !StringUtils.hasText(endD)) {
            return new AddTopicResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        String startTimeStr = startY + startM + startD;
        String endTimeStr = endY + endM + endD;
        int startTimeInt = Integer.parseInt(startTimeStr);
        int endTimeInt = Integer.parseInt(endTimeStr);
        if (startTimeInt > endTimeInt) {
            return new AddTopicResponse(RtnCode.TIME_ERROR.getMessage());
        }
        Topic check = topicDao.findByNameAndStartTimeAndEndTimeAndDescriptionAndNumber(name, startTimeInt, endTimeInt, description,number);
        if (check != null) {
            return new AddTopicResponse(RtnCode.DATA_DUPLICATE.getMessage());
        }

        Topic result = new Topic(number,name, startTimeInt, endTimeInt, description);
        topicDao.save(result);

        return new AddTopicResponse(RtnCode.SUCCESSFUL.getMessage(), result);
    }

    @Override //刪除問卷 順便問題也要刪除
    public DeleteTopicResponse deleteTopic(DeleteTopicRequest request) {
        // 抓使用者現在時間
        String localDateTimeStr = LocalDateTime.now().toString().substring(0,10).replace("-","");
        int localDateTime = Integer.parseInt(localDateTimeStr);
        // Request
        int number = request.getNumber();
        if (number < 0 ){
            return new DeleteTopicResponse(RtnCode.DATA_ERROR.getMessage());
        }
        Topic target = topicDao.findByNumber(number);
        if (target == null){
            return new DeleteTopicResponse(RtnCode.NOT_FOUND.getMessage()); // 該問卷不存在
        }
        if ((localDateTime > target.getStartTime() && localDateTime < target.getEndTime())
            || localDateTime > target.getEndTime()){
            return new DeleteTopicResponse(RtnCode.CAN_NOT_DELETE.getMessage());
        }
        questionDao.deleteByTopicNumber(number);
        topicDao.deleteByNumber(number);

        return new DeleteTopicResponse(RtnCode.SUCCESSFUL.getMessage());
    }
}
/*    字串時間 寫法
    if (!StringUtils.hasText(name) || !StringUtils.hasText(description)
                || !StringUtils.hasText(startY) || !StringUtils.hasText(startM) || !StringUtils.hasText(startD)
                || !StringUtils.hasText(endY) || !StringUtils.hasText(endM) || !StringUtils.hasText(endD)) {
            return new AddTopicResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }

        String startTimeStr = startY + startM + startD;
        String endTimeStr = endY + endM + endD;
        int startTimeInt = Integer.parseInt(startTimeStr);
        int endTimeInt =Integer.parseInt(endTimeStr);
        if (startTimeInt >endTimeInt){
            return new AddTopicResponse(RtnCode.TIME_ERROR.getMessage());
        }

        String startTimeRes = startY + "/" + startM + "/" + startD;
        String endTimeRes = endY + "/" + endM + "/" + endD;

        Topic check = topicDao.findByNameAndStartTimeAndEndTimeAndDescription(name, startTimeRes, endTimeRes, description);
        if (check != null) {
            return new AddTopicResponse(RtnCode.DATA_DUPLICATE.getMessage());
        }

        Topic result = new Topic(name, startTimeRes, endTimeRes, description);
        topicDao.save(result);

        return new AddTopicResponse(RtnCode.SUCCESSFUL.getMessage(), result);
* */