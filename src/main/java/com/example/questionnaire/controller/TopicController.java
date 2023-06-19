package com.example.questionnaire.controller;

import com.example.questionnaire.service.ifs.TopicService;
import com.example.questionnaire.vo.AddTopicRequest;
import com.example.questionnaire.vo.AddTopicResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TopicController {
    @Autowired
    private TopicService topicService;

    @PostMapping(value = "add_topic")
    public AddTopicResponse addTopic(@RequestBody AddTopicRequest request){
        return topicService.addTopic(request);
    }
}
