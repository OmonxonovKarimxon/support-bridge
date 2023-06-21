package com.example.model.dto.answer;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;


@Getter
@Setter
public class AnswerFullInfoDto {
    private Integer id;
    private Integer userId;
    private Integer questionId;
    private  String body;
    private Integer attachmentId;
    private String CreatedDate;
    private String UpdatedDate;


    // id, user_id, question_id, body, attachment_id,
}
