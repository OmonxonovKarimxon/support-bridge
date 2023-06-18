package com.example.repository;


import com.example.model.entity.AnswerEntity;
import com.example.model.entity.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Query("from CommentEntity comment where comment.state= true")
    Page<CommentEntity> pagination(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update CommentEntity comment set comment.state = false where comment.id = ?1")
    Integer deleteByCommentId(int id);


    Optional<CommentEntity> findByIdAndStateIsTrue(Integer commentId);
}



