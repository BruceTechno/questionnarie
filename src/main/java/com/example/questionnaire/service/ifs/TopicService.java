package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.*;

public interface TopicService {
    public AddTopicResponse addTopic (AddTopicRequest request);
    public DeleteTopicResponse deleteTopic(DeleteTopicRequest request);
    public UpdateTopicResponse updateTopic(UpdateTopicRequest request);
    // For HomePage
    public GetTopicInfoResponse getAllTopic ();
    public SearchTopicResponse searchTopic (SearchTopicRequest request);
    public GetTopicInfoResponse getTopicByTopicNumber(GetTopicInfoRequest request);
}
