package com.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {

    private Integer id;
    private Integer userId;
    private Integer questionId;
    private  String body;
    private Integer attachmentId;

}
