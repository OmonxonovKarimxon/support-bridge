package com.example.model.dto.comment;

import com.example.enums.CommentType;
import com.example.model.dto.UserAccountDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateDto {

    private Integer ownerId;
    private  String body;
    private CommentType commentType;

    // Other elements
    private UserAccountDto accountDto;

}
