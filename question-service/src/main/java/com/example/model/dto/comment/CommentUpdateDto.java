package com.example.model.dto.comment;

import com.example.dto.UserAccountDto;
import com.example.enums.CommentType;
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
