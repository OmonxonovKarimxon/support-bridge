package com.example.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Time;
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
    @Column( nullable = false, name = "user_id")
    private Integer userId;
    @Column(nullable = false, name = "question_id")
    private Integer questionId;
    @Column( nullable = false, name = "attachment_id")
    private Integer attachmentId;
    @Column( nullable = false)
    private String body;

    @Column(columnDefinition = "boolean default true")
    private Boolean state;

    @Column
    private Timestamp createdDate ;
    @Column
    private Timestamp updatedDate;
}
 // id,user_id,question_id,body,attachment_id,created_at,updated_at,state.