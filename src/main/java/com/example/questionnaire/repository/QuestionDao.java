package com.example.questionnaire.repository;

import com.example.questionnaire.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer> {
    @Transactional
    @Modifying
    @Query(value = "insert into question(topic_number,question,options,type,must)" +
            " select :topicNumber, :question, :option, :type, :must" +
            " where not exists (select 1 from question where topic_number = :topicNumber and question = :question and options = :option)"
            ,nativeQuery = true)
    public int addQueryWhereNotExists(
            @Param("topicNumber")int number,
            @Param("question")String question,
            @Param("option")String option,
            @Param("type")int type,
            @Param("must")boolean must);
    @Transactional
    public void deleteByTopicNumberAndQuestion (int TopicNumber ,String question);

    @Transactional
    @Modifying
    @Query(value = "update Question q set q.question = :question, q.options = :options, q.type = :type, q.must = :must" +
            " where q.topicNumber = :topicNumber and q.question = :question")
    public int updateQuestionByNumber(
            @Param("question")String question,
            @Param("options")String options,
            @Param("type")int type,
            @Param("must")boolean must,
            @Param("topicNumber")int topicNumber);

    public Question findByTopicNumberAndQuestion (int number , String question);
    public List<Question> findByTopicNumber(int number);
    @Transactional
    public void deleteByTopicNumber(int TopicNumber);

    public List<Question> findAllByTopicNumber(int topicNumber);
}
