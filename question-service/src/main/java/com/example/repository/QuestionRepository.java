package com.example.repository;

import com.example.model.entity.QuestionEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Integer>, JpaSpecificationExecutor<QuestionEntity> {

    @Transactional
    @Modifying
    @Query("update QuestionEntity question set question.description = ?1, question.title = ?2 where question.id = ?3 and question.state = true ")
    Integer editByQuestionId(String description, String title, int questionId);

    @Transactional
    @Modifying
    @Query("update QuestionEntity question set question.state = false where question.id = ?1 and question.state = true")
    void deleteByQuestionId(Integer id);

    @Query("select question from QuestionEntity question where question.state = true")
    List<QuestionEntity> getAll();

    Optional<QuestionEntity> findByIdAndOwnerIdAndStateIsTrue(Integer questionId, Integer userId);

    Optional<QuestionEntity> findByIdAndStateIsTrue(Integer questionId);
}
