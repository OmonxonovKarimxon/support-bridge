package com.company.model.entity;

import com.company.enums.CommentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "comment")
@DynamicUpdate
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column( nullable = false, updatable = false)
    private Integer userId;

    @Column(nullable = false, updatable = false)
    private Integer ownerId;

    @Column(name = "types", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private CommentType commentType;

    @Column(nullable = false)
    private String body;

    @Column
    private Boolean state;

    @Column
    private LocalDateTime createdDate;
    @Column
    private LocalDateTime updatedDate;
}
 //id, user_id, writing_id(question_id or answer_id), body, created_at, updated_at, state.