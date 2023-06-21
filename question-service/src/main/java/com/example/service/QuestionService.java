package com.example.service;

import com.example.dto.PagingDto;
import com.example.dto.QuestionDto;
import com.example.dto.QuestionParamsDto;
import com.example.form.QuestionForm;
import com.example.model.entity.QuestionEntity;

import java.util.List;

public interface QuestionService {

    PagingDto<QuestionDto> getByParams(QuestionParamsDto params);

    QuestionDto add(QuestionForm form);

    QuestionDto edit(QuestionForm form);

    void delete(Integer id);

    QuestionEntity get(Integer questionId, Integer userId);

    QuestionEntity getById(Integer questionId);
}