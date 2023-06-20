package com.example.questionnaire.repository;

import com.example.questionnaire.entity.User;
import com.example.questionnaire.vo.GetDistinctUserResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,Integer> {

    @Transactional
    @Modifying
    @Query("select distinct new com.example.questionnaire.vo.GetDistinctUserResponse(u.name,u.ansTime)" +                                     //
            " from User u" +
            " where u.topicNumber = :topicNumber")
    public List<GetDistinctUserResponse> getUsersWhoAnswerThisTopic (@Param("topicNumber")int topicNumber);


}
