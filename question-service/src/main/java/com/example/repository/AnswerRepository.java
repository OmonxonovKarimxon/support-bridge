package com.example.repository;


import com.example.model.entity.AnswerEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Integer> {

    @Query("from AnswerEntity ")
    Page<AnswerEntity> pagination(Pageable pageable);

//    @Transactional
//    @Modifying
//    @Query("UPDATE  ReportEntity  set visible=false  where id=:reportId")
//     void delete(Integer reportId);



}



