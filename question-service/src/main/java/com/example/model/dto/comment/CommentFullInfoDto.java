package com.example.model.dto.comment;

import com.example.enums.CommentType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CommentFullInfoDto {
    private Integer id;
    private Integer userId;
    private Integer questionId;
    private Integer answerId;
    private  String body;
    private CommentType commentType;
    private String CreatedDate;
    private String UpdatedDate;
    private Boolean state;


}
