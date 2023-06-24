package com.example.service;

<<<<<<< HEAD
import org.springframework.stereotype.Service;


import com.example.model.dto.question.QuestionAddDTO;
import com.example.model.dto.question.QuestionEditDTO;
import com.example.model.dto.question.QuestionResponseDTO;
=======
import com.example.dto.PagingDto;
import com.example.dto.QuestionDto;
import com.example.dto.QuestionParamsDto;
import com.example.form.QuestionForm;
import com.example.model.entity.QuestionEntity;
>>>>>>> origin/coder

import java.util.List;

public interface QuestionService {

    PagingDto<QuestionDto> getByParams(QuestionParamsDto params);

    QuestionDto add(QuestionForm form);

    QuestionDto edit(QuestionForm form);

<<<<<<< HEAD
    List<QuestionResponseDTO> getAll();
=======
    void delete(Integer id);

    QuestionEntity get(Integer questionId, Integer userId);

    QuestionEntity getById(Integer questionId);
>>>>>>> origin/coder
}