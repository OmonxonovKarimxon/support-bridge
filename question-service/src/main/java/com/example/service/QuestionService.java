package com.example.service;

import org.springframework.stereotype.Service;


import com.example.model.dto.question.QuestionAddDTO;
import com.example.model.dto.question.QuestionEditDTO;
import com.example.model.dto.question.QuestionResponseDTO;

import java.util.List;

public interface QuestionService {

    QuestionResponseDTO add(QuestionAddDTO questionAddDTO);

    int edit(QuestionEditDTO questionEditDTO);

    int delete(int id);

    List<QuestionResponseDTO> getAll();
}