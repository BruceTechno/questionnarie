package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.AddTopicRequest;
import com.example.questionnaire.vo.AddTopicResponse;

public interface TopicService {
    public AddTopicResponse addTopic (AddTopicRequest request);

}
