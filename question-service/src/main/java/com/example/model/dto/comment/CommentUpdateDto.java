package com.example.model.dto.comment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDto {
    private  Integer id;
    private Integer userId;
    private Integer questionId;
    private  String body;
    private Integer answerId;
}
