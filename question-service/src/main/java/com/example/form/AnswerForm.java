package com.example.form;

//import com.example.model.dto.UserAccountDto;
import com.example.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerForm {

    private Integer questionId;
    private  String body;
    private Integer attachmentId;

    // Other elements
    private UserAccountDto accountDto;
}
