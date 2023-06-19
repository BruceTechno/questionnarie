package com.example.questionnaire.service.impl;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Topic;
import com.example.questionnaire.repository.TopicDao;
import com.example.questionnaire.service.ifs.TopicService;
import com.example.questionnaire.vo.AddTopicRequest;
import com.example.questionnaire.vo.AddTopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;

    @Override
    public AddTopicResponse addTopic(AddTopicRequest request) {
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
    }
}
