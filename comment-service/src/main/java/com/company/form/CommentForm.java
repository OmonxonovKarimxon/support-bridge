package com.company.form;

import com.company.dto.UserAccountDto;
import com.company.enums.CommentType;
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
