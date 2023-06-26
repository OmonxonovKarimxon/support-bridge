package com.example.repository;

import com.example.model.entity.QuestionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer>, JpaSpecificationExecutor<QuestionEntity> {


    @Transactional
    @Modifying
    @Query("update QuestionEntity question set question.state = false where question.id = ?1 and question.state = true")
    void deleteByQuestionId(Integer id);

    Optional<QuestionEntity> findByIdAndOwnerIdAndStateIsTrue(Integer questionId, Integer userId);

    Optional<QuestionEntity> findByIdAndStateIsTrue(Integer questionId);
}
