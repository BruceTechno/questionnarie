package com.example.questionnaire.repository;

import com.example.questionnaire.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TopicDao extends JpaRepository<Topic,Integer> {
    public Topic findByNameAndStartTimeAndEndTimeAndDescriptionAndNumber (String name,int startTime,int endTime,String description,int number);
    public Topic findByNameAndStartTimeAndEndTimeAndDescription (String name,int startTime,int endTime,String description);
    public Topic findByNumber(int number);

    // For Method => getUsersWhoAnswerThisTopic
    public boolean existsByNumber(int number);
    @Transactional
    public void deleteByNumber(int number);
}
