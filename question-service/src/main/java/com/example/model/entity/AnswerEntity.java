package com.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "answer")
@DynamicInsert
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false, name = "user_id")
    private Integer userId;
    @Column(unique = true, nullable = false, name = "question_id")
    private Integer questionId;
    @Column(unique = true, nullable = false)
    private String body;

    @Column(columnDefinition = "boolean default true")
    private Boolean state;

    @CreationTimestamp
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;
}
 // id,user_id,question_id,body,attachment_id,created_at,updated_at,state.