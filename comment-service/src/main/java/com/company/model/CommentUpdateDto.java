package com.company.model;

import com.company.dto.UserAccountDto;
import com.company.enums.CommentType;

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
