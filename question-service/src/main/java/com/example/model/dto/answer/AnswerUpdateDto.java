package com.example.model.dto.answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerUpdateDto {
    private Integer userId;
    private Integer questionId;
    private  String body;
    private Integer attachmentId;
}
