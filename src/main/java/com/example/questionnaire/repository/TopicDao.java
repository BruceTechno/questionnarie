package com.example.questionnaire.repository;

import com.example.questionnaire.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicDao extends JpaRepository<Topic,Integer> {
    public Topic findByNameAndStartTimeAndEndTimeAndDescription (String name,int startTime,int endTime,String description);

}
