package com.example.questionnaire.controller;

import com.example.questionnaire.service.ifs.QuestionService;
import com.example.questionnaire.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping(value = "update_question")
//    @RequestMapping(value = "/update_question", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public UpdateQuestionResponse updateQuestion(@RequestBody UpdateQuestionRequest request) {
        return questionService.updateQuestion(request);
    }
    @PostMapping(value = "get_question_info")
    public GetQuestionResponse getQuestionInfo(@RequestBody GetQuestionRequest request) {
        return questionService.getQuestionInfo(request);
    }
}
