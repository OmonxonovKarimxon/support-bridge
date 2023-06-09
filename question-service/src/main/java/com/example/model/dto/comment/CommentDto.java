package com.example.model.dto.comment;

import com.example.enums.CommentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Integer id;
    private Integer ownerId;
    private String body;
    private CommentType commentType;
    private LocalDateTime createdDate;
}
