package com.example.controller;


import com.example.model.dto.question.QuestionAddDTO;
import com.example.model.dto.question.QuestionEditDTO;
import com.example.model.dto.question.QuestionResponseDTO;
import com.example.model.dto.user.UserAccountDto;
import com.example.proxy.AuthProxy;
import com.example.service.QuestionService;
import com.example.service.QuestionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionServiceImpl questionService;
//    private final QuestionService questionServices;


    @PostMapping("/add")
    public ResponseEntity<QuestionResponseDTO> add(
            @RequestBody QuestionAddDTO questionAddDTO
    ) {
        QuestionResponseDTO responseDTO = questionService.add(questionAddDTO);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/update")
    public ResponseEntity<?> edit(
            @RequestBody QuestionEditDTO questionEditDTO
    ) {

        int res = questionService.edit(questionEditDTO);

        String message = res == 0 ? "failed" : "success";

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int id
    ) {

        int res = questionService.delete(id);

        String message = res == 0 ? "failed" : "success";

        return ResponseEntity.ok(message);
    }

    @GetMapping("/all")
    public ResponseEntity<List<QuestionResponseDTO>> getAll() {

        return ResponseEntity.ok().body(questionService.getAll());
    }


    @GetMapping("/profile")
    public UserAccountDto getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        return questionService.gett(authHeader);
    }
}
