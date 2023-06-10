package com.example.model.dto.answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerCreateDto {
    private Integer id;
    private Integer userId;
    private Integer questionId;
    private  String body;
    private Integer attachmentId;

    // id, user_id, question_id, body, attachment_id,
}
