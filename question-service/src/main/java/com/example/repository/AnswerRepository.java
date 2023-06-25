package com.example.repository;


import com.example.model.entity.AnswerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {

    @Query("from AnswerEntity answer where answer.state= true")
    Page<AnswerEntity> pagination(Pageable pageable);

    @Transactional
    @Modifying
    @Query("update AnswerEntity answer set answer.state = false where answer.id = ?1")
    Integer deleteByAnswerId(int id);


    Optional<AnswerEntity> findByIdAndStateIsTrue(Integer id);

}



