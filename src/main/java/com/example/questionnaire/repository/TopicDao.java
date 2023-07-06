package com.example.questionnaire.repository;

import com.example.questionnaire.entity.Topic;
import com.example.questionnaire.vo.DistinctSearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TopicDao extends JpaRepository<Topic,Integer> {
    public Topic findByNameAndStartTimeAndEndTimeAndDescriptionAndNumber (String name,int startTime,int endTime,String description,int number);
    public Topic findByNameAndStartTimeAndEndTimeAndDescription (String name,int startTime,int endTime,String description);
    public Topic findByNumber(int number);

    // For Method => getUsersWhoAnswerThisTopic
    public boolean existsByNumber(int number);
    @Transactional
    public void deleteByNumber(int number);
    @Transactional
    @Modifying
    @Query(value = "update Topic t set t.name = :name, t.startTime = :startTime, t.endTime = :endTime, t.description = :description" +
            " where t.number = :number")
    public int updateTopicByNumber(
            @Param("name")String name,
            @Param("startTime")int startTime,
            @Param("endTime")int endTime,
            @Param("description")String description,
            @Param("number")int number);
    @Transactional
    @Modifying
    @Query("select new com.example.questionnaire.vo.DistinctSearchResult(t.number,t.name,t.startTime,t.endTime,t.description)" +            //==
            " from Topic t" +
            " where (t.name like concat('%',:inputName,'%') or :inputName is NULL)" +
            " and (t.startTime >= :inputStartTime or :inputStartTime is 0)" +
            " and (t.endTime <= :inputEndTime or :inputEndTime is 0)")
    public List<DistinctSearchResult> distinctSearchTopic(
            @Param("inputName")String inputName,
            @Param("inputStartTime")int inputStartTime,
            @Param("inputEndTime")int inputEndTime);
}
