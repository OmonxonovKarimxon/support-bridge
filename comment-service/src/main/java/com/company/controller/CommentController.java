package com.company.controller;

import com.company.dto.UserAccountDto;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {



    @PostMapping
    public String testApi(Authentication authentication){
        UserAccountDto accountDto = (UserAccountDto) authentication.getDetails();
        return accountDto.getUsername();
    }
}
