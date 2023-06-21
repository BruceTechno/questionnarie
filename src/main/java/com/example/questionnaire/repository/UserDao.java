package com.example.questionnaire.repository;

import com.example.questionnaire.entity.User;
import com.example.questionnaire.vo.GetDistinctUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query("select distinct new com.example.questionnaire.vo.GetDistinctUserResponse(u.name,u.ansTime)" +                                     //
            " from User u" +
            " where u.topicNumber = :topicNumber")
    public List<GetDistinctUserResponse> getUsersWhoAnswerThisTopic (@Param("topicNumber")int topicNumber);

    public List<User> findByNameAndAnsTime(String name , LocalDateTime ansTime);
    public boolean existsByMailAndQuestion(String mail , String question);

//    @Transactional
//    @Modifying
//    @Query(value = "insert into user(name,phone,mail,age,topic_number,question,answer,ans_time)" +
//            " select :name, :phone, :mail, :age, :topicNumber, :question, :answer, :ansTime" +
//            " where not exists (select 1 from question where topic_number = :topicNumber)"
//            ,nativeQuery = true)
//    public int addUserInfoWhereNotExists(
//            @Param("name")String name,
//            @Param("phone")String phone,
//            @Param("mail")String mail,
//            @Param("age")int age,
//            @Param("topicNumber")int topicNumber,
//            @Param("question")String question,
//            @Param("answer")String answer,
//            @Param("ansTime")LocalDateTime ansTime,
//            @Param("topic");

}
