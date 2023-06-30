package com.example.questionnaire.service.impl;

import com.example.questionnaire.constants.RtnCode;
import com.example.questionnaire.entity.Question;
import com.example.questionnaire.entity.Topic;
import com.example.questionnaire.repository.QuestionDao;
import com.example.questionnaire.repository.TopicDao;
import com.example.questionnaire.service.ifs.TopicService;
import com.example.questionnaire.vo.*;
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
        String localDateTimeStr = LocalDateTime.now().toString().substring(0, 10).replace("-", "");
        int localDateTime = Integer.parseInt(localDateTimeStr);
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
        //多判斷一個現在時間 不可比開始時間晚 不可新增已經過期的問卷
        if (startTimeInt > endTimeInt) {
            return new AddTopicResponse(RtnCode.TIME_ERROR.getMessage());
        }
        if (localDateTime > startTimeInt){
            return new AddTopicResponse(RtnCode.CAN_NOT_ADD_NOW.getMessage());
        }
        // 因為現在 number還是存在session 所以無法 exists by number
        Topic check = topicDao.findByNameAndStartTimeAndEndTimeAndDescriptionAndNumber(name, startTimeInt, endTimeInt, description, number);
        if (check != null) {
            return new AddTopicResponse(RtnCode.DATA_DUPLICATE.getMessage());
        }

        Topic result = new Topic(number, name, startTimeInt, endTimeInt, description);
        topicDao.save(result);

        return new AddTopicResponse(RtnCode.SUCCESSFUL.getMessage(), result);
    }

    @Override //刪除問卷 順便問題也要刪除
    public DeleteTopicResponse deleteTopic(DeleteTopicRequest request) {
        // 抓使用者現在時間    2023-06-20T15:04:33.537196500 => 20230620
        String localDateTimeStr = LocalDateTime.now().toString().substring(0, 10).replace("-", "");
        int localDateTime = Integer.parseInt(localDateTimeStr);
        // Request
        int number = request.getNumber();
        if (number < 0) {
            return new DeleteTopicResponse(RtnCode.DATA_ERROR.getMessage());
        }
        Topic target = topicDao.findByNumber(number);
        if (target == null) {
            return new DeleteTopicResponse(RtnCode.NOT_FOUND.getMessage()); // 該問卷不存在
        }
        // 使用者現在時間 > 欲刪除之問卷的開始時間 且 使用者現在時間 < 問卷之結束時間 => 表示問卷開放中
        if ((localDateTime > target.getStartTime() && localDateTime < target.getEndTime())
                || localDateTime > target.getEndTime()) {// 使用者現在時間 > 欲刪除問卷之結束時間 => 表示 問卷已關閉
            return new DeleteTopicResponse(RtnCode.CAN_NOT_DELETE.getMessage());
        }
        questionDao.deleteByTopicNumber(number);
        topicDao.deleteByNumber(number);

        return new DeleteTopicResponse(RtnCode.SUCCESSFUL.getMessage());
    }

    @Override
    public UpdateTopicResponse updateTopic(UpdateTopicRequest request) {
        // 抓使用者現在時間    2023-06-20T15:04:33.537196500 => 20230620
        String localDateTimeStr = LocalDateTime.now().toString().substring(0, 10).replace("-", "");
        int localDateTime = Integer.parseInt(localDateTimeStr);
        // Request
        int number = request.getNumber();
        String name = request.getName();
        String description = request.getDescription();
        String startY = request.getStartY();
        String startM = request.getStartM();
        String startD = request.getStartD();
        String endY = request.getEndY();
        String endM = request.getEndM();
        String endD = request.getEndD();
        if (number < 0) {
            return new UpdateTopicResponse(RtnCode.DATA_ERROR.getMessage());
        }
        if (!StringUtils.hasText(name) || !StringUtils.hasText(description)
                || !StringUtils.hasText(startY) || !StringUtils.hasText(startM) || !StringUtils.hasText(startD)
                || !StringUtils.hasText(endY) || !StringUtils.hasText(endM) || !StringUtils.hasText(endD)) {
            return new UpdateTopicResponse(RtnCode.CANNOT_EMPTY.getMessage());
        }
        // 這裡是欲更新之時間的防呆 => 開始時間 不能大於 結束時間
        String startTimeStr = startY + startM + startD;
        String endTimeStr = endY + endM + endD;
        int startTimeInt = Integer.parseInt(startTimeStr);
        int endTimeInt = Integer.parseInt(endTimeStr);
        if (startTimeInt > endTimeInt) {
            return new UpdateTopicResponse(RtnCode.TIME_ERROR.getMessage());
        }
        // 原始問卷
        Topic target = topicDao.findByNumber(number);
        if (target == null){
            return new UpdateTopicResponse(RtnCode.NOT_FOUND.getMessage());//沒有這份問卷 無法編輯
        }
        // 問卷狀態判斷
        // 使用者現在時間 > 欲刪除之問卷的開始時間 且 使用者現在時間 < 問卷之結束時間 => 表示問卷開放中
        if ((localDateTime > target.getStartTime() && localDateTime < target.getEndTime())
                || localDateTime > target.getEndTime()) {// 使用者現在時間 > 欲刪除問卷之結束時間 => 表示 問卷已關閉
            return new UpdateTopicResponse(RtnCode.CAN_NOT_UPDATE.getMessage());
        }
//        // 時間防呆 => 假設問卷狀態為關閉中 => 欲更新之問卷開放時間 不可早於 原本問卷開放之時間 updateStartTime < oldStartTime
//        if ((startTimeInt < target.getStartTime() && localDateTime > target.getEndTime())){
//            return new UpdateTopicResponse(RtnCode.START_TIME_ERROR.getMessage());
//        }
        int result = topicDao.updateTopicByNumber(name, startTimeInt, endTimeInt, description, number);
        if (result == 0){
            return new UpdateTopicResponse(RtnCode.UPDATE_FAILED.getMessage());
        }
        Topic updateContent = new Topic(number,name,startTimeInt,endTimeInt,description);
        return new UpdateTopicResponse(RtnCode.SUCCESSFUL.getMessage(),updateContent);
    }

    @Override
    public GetTopicInfoResponse getAllTopic() {
        return new GetTopicInfoResponse(RtnCode.SUCCESSFUL.getMessage(),topicDao.findAll());
    }

    @Override
    public SearchTopicResponse searchTopic(SearchTopicRequest request) {
        return null;
    }

    @Override
    public GetTopicInfoResponse getTopicByTopicNumber(GetTopicInfoRequest request) {
        int number = request.getNumber();
        if (number < 0 ){
            return new GetTopicInfoResponse(RtnCode.DATA_ERROR.getMessage());
        }
        Topic result = topicDao.findByNumber(number);
        if (result == null ){
            return new GetTopicInfoResponse(RtnCode.NOT_FOUND.getMessage());
        }

        return new GetTopicInfoResponse(RtnCode.SUCCESSFUL.getMessage(),result);
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