package com.example.repository;

import com.example.model.entity.SavedQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SavedQuestionRepository extends JpaRepository<SavedQuestionEntity, Integer> {

    int deleteByQuestionIdAndUserId(int questionId, int userId);

    Optional<SavedQuestionEntity> findByQuestionIdAndUserId(int questionId, int userId);
}
