package com.example.model.dto.question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class QuestionEditDTO {

    private String newTitle;
    private String newDescription;
    private int questionId;
    //who editing it will be added then
}
