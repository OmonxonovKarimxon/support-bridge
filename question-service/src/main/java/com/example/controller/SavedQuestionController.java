package com.example.controller;

import com.example.service.SavedQuestionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saved-question")
@AllArgsConstructor
public class SavedQuestionController {

    private final SavedQuestionServiceImpl savedQuestionService;

    @PostMapping("/save/{id}")
    public ResponseEntity<?> save(
            @PathVariable("id") int question_id
    ) {

        String message = savedQuestionService.add(question_id) ? "success" : "failed";

        return ResponseEntity.ok(message);
    }


    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> remove(
            @PathVariable("id") int question_id
    ) {

        String message = savedQuestionService.remove(question_id) ? "success" : "failed";

        return ResponseEntity.ok(message);
    }
}
