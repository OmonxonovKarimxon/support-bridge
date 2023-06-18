package com.example.service;


import com.example.model.dto.question.QuestionAddDTO;
import com.example.model.dto.question.QuestionEditDTO;
import com.example.model.dto.question.QuestionResponseDTO;
import com.example.model.entity.QuestionEntity;

import java.util.List;

public interface QuestionService {

    QuestionResponseDTO add(QuestionAddDTO questionAddDTO);

    int edit(QuestionEditDTO questionEditDTO);

    int delete(int id);

    List<QuestionResponseDTO> getAll();

    QuestionEntity get(Integer id);
}