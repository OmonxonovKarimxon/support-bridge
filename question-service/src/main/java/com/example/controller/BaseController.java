package com.example.controller;

<<<<<<< HEAD
=======
import com.example.dto.UserAccountDto;
>>>>>>> origin/coder
import com.example.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
<<<<<<< HEAD
@RequestMapping("/question")
=======
@RequestMapping
>>>>>>> origin/coder
public class BaseController {

    @Autowired
    private QuestionService questionService;


    @PostMapping
    public String add(Authentication authentication){
        UserAccountDto account = (UserAccountDto) authentication.getDetails();
        return account.getUsername();
    }

}
