package com.example.model.entity;

import com.example.enums.CommentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
@DynamicInsert
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column( nullable = false, name = "user_id")
    private Integer userId;
    @Column( name = "question_id")
    private Integer questionId;
    @Column(  name = "answer_id")
    private Integer answerId;
    @Column( nullable = false)
    private String body;
    @Column(name = "types", nullable = false)
    @Enumerated(EnumType.STRING)
    private CommentType commentType;

    @Column(columnDefinition = "boolean default true")
    private Boolean state;

    @Column
    private Timestamp createdDate ;
    @Column
    private Timestamp updatedDate;
}
 //id, user_id, writing_id(question_id or answer_id), body, created_at, updated_at, state.