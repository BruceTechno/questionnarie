package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.AddTopicRequest;
import com.example.questionnaire.vo.AddTopicResponse;
import com.example.questionnaire.vo.DeleteTopicRequest;
import com.example.questionnaire.vo.DeleteTopicResponse;

public interface TopicService {
    public AddTopicResponse addTopic (AddTopicRequest request);

    public DeleteTopicResponse deleteTopic(DeleteTopicRequest request);
}
