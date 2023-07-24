package com.example.questionnaire.repository;

import com.example.questionnaire.entity.User;
import com.example.questionnaire.vo.GetDistinctUserResponse;
import com.example.questionnaire.vo.GetUserInfoResponse;
import com.example.questionnaire.vo.StatisticsResult;
import com.example.questionnaire.vo.UserAndQuestionJoinResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {


    @Query("select distinct new com.example.questionnaire.vo.GetDistinctUserResponse(u.name,u.ansTime)" +                                     //
            " from User u" +
            " where u.topicNumber = :topicNumber")
    public List<GetDistinctUserResponse> getUsersWhoAnswerThisTopic (@Param("topicNumber")int topicNumber);

    public List<User> findByNameAndAnsTimeAndTopicNumber(String name , LocalDate ansTime,int topicNumber);
    public boolean existsByMailAndQuestion(String mail , String question);

    @Transactional
    @Modifying
    @Query("select new com.example.questionnaire.vo.UserAndQuestionJoinResponse(u.name,u.phone,u.mail,u.age,u.topicNumber,u.question,u.answer,u.ansTime,q.options,q.type,q.must)" +        //03.0
            " from User u join Question q on q.topicNumber = u.topicNumber" +
            " where u.topicNumber = :newTopicNumber")
    public List<UserAndQuestionJoinResponse> getUserAndQuestionInfo(
            @Param("newTopicNumber")int newTopicNumber);



    @Query("select count(*)" +
            " from User u where u.topicNumber = :topicNumber and u.answer = :answer and u.question = :question")
    public int getStatisticsByTopicNumberAndAnswer(
            @Param("topicNumber")int newTopicNumber,
            @Param("answer")String answer,
            @Param("question")String question);
    @Query("select count(*)" +
            " from User u where u.topicNumber = :topicNumber and u.answer like concat('%',:answer,'%') and u.question = :question")
    public int getStatisticsByTopicNumberAndAnswerForMultiple(
            @Param("topicNumber")int newTopicNumber,
            @Param("answer")String answer,
            @Param("question")String question);

//        @Query(value = "SELECT * FROM questionnaire where case when ?1 is not null then title like %?1%"
//            + " else true end"
//            + " and case when ?2 is not null then start_date >= ?2"
//            + " else true end"
//            + " and case when ?3 is not null then end_date <= ?3"
//            + " else true end",
//            nativeQuery = true)
//    public List<Questionnaire> selectQuestionnaire(
//            String title,
//            LocalDate startDate,
//            LocalDate endDate);

//    @Transactional
//    @Modifying
//    @Query("select new com.example.questionnaire.vo.UserAndQuestionJoinResponse(u.name,u.phone,u.mail,u.age,u.topicNumber,u.question,u.answer,u.ansTime,q.options,q.type,q.must)" +        //03.0
//            " from User u join Question q on q.topicNumber = u.topicNumber" +
//            " where u.topicNumber = :newTopicNumber and u.name = :name and u.ansTime = :ansTime")
//    public List<UserAndQuestionJoinResponse> getUserAndQuestionInfo(
//            @Param("newTopicNumber")int newTopicNumber,
//            @Param("name")String name,
//            @Param("ansTime")LocalDateTime ansTime);

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
