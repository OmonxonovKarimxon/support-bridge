package com.company.model;

import com.company.enums.CommentType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentCreateDto {

    private Integer userId;
    private String body;
    private Integer ownerId;

    private CommentType type;


    // id, user_id, question_id, body, attachment_id,
}
