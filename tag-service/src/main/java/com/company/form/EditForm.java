package com.company.form;

import com.company.dto.UserAccountDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditForm {

    private String name;
    private int oldTagId;
    private UserAccountDto userAccountDto;
}
