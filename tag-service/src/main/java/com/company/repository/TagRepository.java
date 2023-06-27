package com.company.repository;

import com.company.entity.TagEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<TagEntity, Integer> {

    @Query("select tag from TagEntity tag where tag.state = true and tag.name = ?1")
    TagEntity findByName(String name);

    @Query("select tag from TagEntity tag where tag.state = true")
    List<TagEntity> getList();

    @Transactional
    @Modifying
    @Query("update TagEntity tag set tag.state = false, tag.updatedBy = ?1 where tag.id = ?2")
    Integer deleteTagById(int updater, int id);


    @Transactional
    @Modifying
    @Query("update TagEntity tag set tag.name = ?1, tag.updatedBy=?2 where tag.id = ?3 and tag.state = true")
    Integer editTagById(String name, int updater, int id);
}
