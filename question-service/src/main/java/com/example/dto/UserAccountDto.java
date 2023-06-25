package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDto {

    private Integer id;
    private String username;
    private String email;
    private LocalDateTime createdAt;

}
