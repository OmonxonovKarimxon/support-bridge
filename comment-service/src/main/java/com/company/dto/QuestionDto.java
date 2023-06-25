package com.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Integer ownerId;
    private Integer id;
    private String title;
    private String description;
    private Timestamp createdAt;

}
