package com.company.controller;


import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler({BadRequestException.class, ItemNotFoundException.class})
    public ResponseEntity<String> handler(RuntimeException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
