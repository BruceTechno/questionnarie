package com.example.questionnaire.service.ifs;

import com.example.questionnaire.vo.*;

public interface QuestionService {
    public AddQuestionResponse addQuestion (AddQuestionRequest request);
    public DeleteQuestionResponse deleteQuestion (DeleteQuestionRequest request);
    public UpdateQuestionResponse updateQuestion (UpdateQuestionRequest request);
    public GetQuestionResponse getQuestionInfo(GetQuestionRequest request);
}
