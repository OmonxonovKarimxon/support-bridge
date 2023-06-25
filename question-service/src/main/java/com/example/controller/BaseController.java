package com.example.controller;

import com.example.dto.UserAccountDto;
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BaseController {

    private final QuestionService questionService;

    public BaseController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @PostMapping
    public String add(Authentication authentication){
        UserAccountDto account = (UserAccountDto) authentication.getDetails();
        return account.getUsername();
    }

}
