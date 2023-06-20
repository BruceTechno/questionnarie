package com.example.questionnaire.controller;

import com.example.questionnaire.service.ifs.TopicService;
import com.example.questionnaire.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping(value = "add_topic")
    public AddTopicResponse addTopic(@RequestBody AddTopicRequest request){
        return topicService.addTopic(request);
    }
    @PostMapping(value = "delete_topic")
    public DeleteTopicResponse deleteTopic(@RequestBody DeleteTopicRequest request) {
        return topicService.deleteTopic(request);
    }
    @PostMapping(value = "update_topic")
    public UpdateTopicResponse updateTopic(@RequestBody UpdateTopicRequest request) {
        return topicService.updateTopic(request);
    }
    @GetMapping(value = "get_all_topic")
    public GetTopicInfoResponse getAllTopic() {
        return topicService.getAllTopic();
    }
}
