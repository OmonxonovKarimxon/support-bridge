package com.example.form;

import com.example.enums.CommentType;
import com.example.model.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {

    private Integer ownerId;
    private  String body;
    private CommentType commentTypes;

    // Other elements
    private UserAccountDto accountDto;
}
