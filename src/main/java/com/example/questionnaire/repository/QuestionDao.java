package com.example.questionnaire.repository;

import com.example.questionnaire.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
}
