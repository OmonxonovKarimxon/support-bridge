package com.example.form;

import com.example.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionForm {

    private String title;
    private String description;

    private UserAccountDto account;
}
