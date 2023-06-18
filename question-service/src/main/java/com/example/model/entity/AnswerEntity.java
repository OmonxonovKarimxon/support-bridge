package com.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "answer")
@DynamicUpdate
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, updatable = false)
    private Integer userId;
    @Column(nullable = false, updatable = false)
    private Integer questionId;
    @Column(nullable = false)
    private Integer attachmentId;
    @Column(nullable = false)
    private String body;

    @Column
    private Boolean state;

    @Column
    private Timestamp createdDate;
    @Column
    private Timestamp updatedDate;
}
// id,user_id,question_id,body,attachment_id,created_at,updated_at,state.