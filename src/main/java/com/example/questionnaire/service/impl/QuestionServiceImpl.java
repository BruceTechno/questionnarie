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
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private TopicDao topicDao;

    @Override //改多筆新增
    public AddQuestionResponse addQuestion(AddQuestionRequest request) {
        //抓使用者現在時間
        String localDateTimeStr = LocalDateTime.now().toString().substring(0,10).replace("-","");
        int localDateTime = Integer.parseInt(localDateTimeStr);
        // Request
        int number = request.getNumber();
        List<Question> questionList = request.getQuestionList();
        if (CollectionUtils.isEmpty(questionList)){
            return new AddQuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        if (number < 0 ){
            return new AddQuestionResponse(RtnCode.DATA_ERROR.getMessage());
        }
        boolean checkTopic = topicDao.existsByNumber(number);
        if (checkTopic == false){
            return new AddQuestionResponse(RtnCode.NOT_FOUND.getMessage()); //問卷可能新增失敗 或無這份問卷
        }
        for (Question item : questionList){
            if (item.getTopicNumber() < 0 || !StringUtils.hasText(item.getQuestion()) ||
                    !StringUtils.hasText(item.getOptions()) || item.getType() < 0 ){
                return new AddQuestionResponse(RtnCode.DATA_ERROR.getMessage());
            }
        }
        questionDao.saveAll(questionList);

        return new AddQuestionResponse(RtnCode.SUCCESSFUL.getMessage());

    }

    @Override
    public DeleteQuestionResponse deleteQuestion(DeleteQuestionRequest request) {
        // 資料庫裡面不會有 問卷 、問題 重複 所以帶這兩個
        int number = request.getNumber();
        String question = request.getQuestion();

        if (number < 0 ){
            return new DeleteQuestionResponse(RtnCode.DATA_ERROR.getMessage());
        }
        if (!StringUtils.hasText(question)){
            return new DeleteQuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        // 以 該問卷之topic number 去尋找出 目前這份問卷是 未開放 已開放 已關閉 哪一個狀態
        Topic topic = topicDao.findByNumber(number);
        if (topic == null){
            return new DeleteQuestionResponse(RtnCode.NOT_FOUND.getMessage());//根本沒這個問卷
        }

        // 時間判斷
        String localDateTimeStr = LocalDateTime.now().toString().substring(0,10).replace("-","");
        int localDateTime = Integer.parseInt(localDateTimeStr);

        if (localDateTime > topic.getStartTime() && localDateTime < topic.getEndTime()){
            return new DeleteQuestionResponse(RtnCode.CAN_NOT_DELETE.getMessage());
        }
        questionDao.deleteByTopicNumberAndQuestion(number,question);


        return new DeleteQuestionResponse(RtnCode.SUCCESSFUL.getMessage());
    }

    @Override
    public UpdateQuestionResponse updateQuestion(UpdateQuestionRequest request) {
        int number = request.getNumber();
        String oldQuestion = request.getOldQuestion();
        String newQuestion = request.getNewQuestion();
        String options = request.getOptions();
        int type = request.getType();
        boolean must = request.isMust();

        if (number < 0 || type < 0 || type > 2  ){
            return new UpdateQuestionResponse(RtnCode.DATA_ERROR.getMessage());
        }
        if (!StringUtils.hasText(oldQuestion) || !StringUtils.hasText(newQuestion) || !StringUtils.hasText(options)){
            return new UpdateQuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        Topic topic = topicDao.findByNumber(number);
        if (topic == null ){
            return new UpdateQuestionResponse(RtnCode.NOT_FOUND.getMessage());
        }
        // 時間判斷
        String localDateTimeStr = LocalDateTime.now().toString().substring(0,10).replace("-","");
        int localDateTime = Integer.parseInt(localDateTimeStr);

        if (localDateTime > topic.getStartTime() && localDateTime < topic.getEndTime()){
            return new UpdateQuestionResponse(RtnCode.CAN_NOT_UPDATE.getMessage());
        }
        int result = questionDao.updateQuestionByNumber(newQuestion,options,type,must,number,oldQuestion);
        if (result == 0){
            return new UpdateQuestionResponse(RtnCode.UPDATE_FAILED.getMessage());
        }
        Question updateContent = new Question(newQuestion,options,type,must);
        return new UpdateQuestionResponse(RtnCode.SUCCESSFUL.getMessage(),updateContent);
    }

    @Override
    public GetQuestionResponse getQuestionInfo(GetQuestionRequest request) {
        int number = request.getNumber();
//        String question = request.getQuestion();
        if (number < 0 ) {
            return new GetQuestionResponse(RtnCode.DATA_ERROR.getMessage());
        }
//        if (!StringUtils.hasText(question)){
//            return  new GetQuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
//        }
        List<Question> result = questionDao.findByTopicNumber(number);
        if (result == null){
            return new GetQuestionResponse(RtnCode.NOT_FOUND.getMessage());
        }

        return new GetQuestionResponse(RtnCode.SUCCESSFUL.getMessage(),result);
    }

}
/*
*
//原本 add question 的寫法
//        int number = request.getNumber();
//        String question = request.getQuestion();
//        String options = request.getOptions();
//        int type = request.getType();
//        boolean must = request.isMust();
//        int endTime = request.getEndTime();
//        if (number < 0 || type < 0 || type > 2 ){
//            return new AddQuestionResponse(RtnCode.DATA_ERROR.getMessage());
//        }
//        if (!StringUtils.hasText(question) || !StringUtils.hasText(options)){
//            return new AddQuestionResponse(RtnCode.CANNOT_EMPTY.getMessage());
//        }
//
//        // 時間判斷 => 關閉了就不能新增
//        String localDateTimeStr = LocalDateTime.now().toString().substring(0,10).replace("-","");
//        int localDateTime = Integer.parseInt(localDateTimeStr);
//
//        // 關閉了就不用再新增了  只有 開放中 => 問題不夠可以新增 開放前 => 可以新增
//        if (localDateTime > endTime){
//            return new AddQuestionResponse(RtnCode.ALREADY_CLOSER.getMessage());
//        }
//        // 問卷編號 問題 選項 若一樣則不給新增
//        int result = questionDao.addQueryWhereNotExists(number, question, options, type, must);
//        if (result == 0){
//            return new AddQuestionResponse(RtnCode.DATA_DUPLICATE.getMessage());
//        }
* */