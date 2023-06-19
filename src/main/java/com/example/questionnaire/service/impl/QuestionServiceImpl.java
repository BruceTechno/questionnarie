package com.example.questionnaire.service.impl;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Topic;
import com.example.questionnaire.repository.QuestionDao;
import com.example.questionnaire.repository.TopicDao;
import com.example.questionnaire.service.ifs.QuestionService;
import com.example.questionnaire.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private TopicDao topicDao;

    @Override
    public AddQuestionResponse addQuestion(AddQuestionRequest request) {
        String name = request.getName();
        int startTime = request.getStartTime();
        int endTime = request.getEndTime();
        String description = request.getDescription();

        Topic topic = topicDao.findByNameAndStartTimeAndEndTimeAndDescription(name, startTime, endTime, description);
        if (topic == null){
            return new AddQuestionResponse(RtnCode.NOT_FOUND.getMessage());
        }

        int number = topic.getNumber();
        String question = request.getQuestion();
        String options = request.getOptions();
        int type = request.getType();
        boolean isMust = request.isMust();
        if (number < 0 || type < 0 || type > 2){
            return new AddQuestionResponse(RtnCode.DATA_ERROR.getMessage());
        }
        if (!StringUtils.hasText(question) || !StringUtils.hasText(options)){
            return new AddQuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        // 時間判斷 開放 未開放 已關閉
        String localDateTimeStr = LocalDateTime.now().toString().substring(0,10).replace("-","");
        int localDateTime = Integer.parseInt(localDateTimeStr);

        if (localDateTime < startTime){
            return new AddQuestionResponse(RtnCode.NOT_OPEN_YET.getMessage());
        }
        if (localDateTime > endTime){
            return new AddQuestionResponse(RtnCode.ALREADY_CLOSER.getMessage());
        }
        // 問卷編號 問題 選項 若一樣則不給新增
        int result = questionDao.addQueryWhereNotExists(number, question, options, type, isMust);
        if (result == 0){
            return new AddQuestionResponse(RtnCode.DATA_DUPLICATE.getMessage());
        }
        return new AddQuestionResponse(RtnCode.SUCCESSFUL.getMessage());
    }

    @Override
    public DeleteQuestionResponse deleteQuestion(DeleteQuestionRequest request) {
        int number = request.getNumber();
        String question = request.getQuestion();

        // 以 該問卷之topic number 去尋找出 目前這份問卷是 未開放 已開放 已關閉 哪一個狀態
        Optional<Topic> topic = topicDao.findById(number);
        if (!topic.isPresent()){
            return new DeleteQuestionResponse(RtnCode.NOT_FOUND.getMessage());
        }

        // 時間判斷
        String localDateTimeStr = LocalDateTime.now().toString().substring(0,10).replace("-","");
        int localDateTime = Integer.parseInt(localDateTimeStr);

        if (localDateTime > topic.get().getStartTime() && localDateTime < topic.get().getEndTime()){
            return new DeleteQuestionResponse(RtnCode.CAN_NOT_DELETE.getMessage());
        }
        questionDao.deleteByTopicNumberAndQuestion(number,question);


        return new DeleteQuestionResponse(RtnCode.SUCCESSFUL.getMessage());
    }

}
