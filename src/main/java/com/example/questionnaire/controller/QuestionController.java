package com.example.questionnaire.controller;

import com.example.questionnaire.service.ifs.QuestionService;
import com.example.questionnaire.vo.AddQuestionRequest;
import com.example.questionnaire.vo.AddQuestionResponse;
import com.example.questionnaire.vo.DeleteQuestionRequest;
import com.example.questionnaire.vo.DeleteQuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @PostMapping(value = "add_question")
    public AddQuestionResponse addQuestion(@RequestBody AddQuestionRequest request) {
        return questionService.addQuestion(request);
    }
    @PostMapping(value = "delete_question")
    public DeleteQuestionResponse deleteQuestion(@RequestBody DeleteQuestionRequest request) {
        return questionService.deleteQuestion(request);
    }
}
