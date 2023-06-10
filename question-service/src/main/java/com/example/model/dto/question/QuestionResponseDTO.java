package com.example.model.dto.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionResponseDTO {

    private int ownerId;
    private String description;
    private String title;
    private int id;
    private Timestamp created_at;
}
