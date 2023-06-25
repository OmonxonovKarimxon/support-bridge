package com.example.service;

import com.example.form.AnswerForm;
import com.example.model.dto.AnswerDto;
import com.example.model.dto.answer.AnswerFullInfoDto;
import com.example.model.entity.AnswerEntity;
import org.springframework.data.domain.Page;

public interface AnswerService {

    String create(AnswerForm dto);

    String update(AnswerForm dto, Integer answerId);

    String delete(Integer id);

    Page<AnswerDto> getListForUser(Integer pagenumber, Integer pageSize);

    AnswerDto getAnswerById(Integer id);

    Page<AnswerFullInfoDto> getListForAdmin(Integer pagenumber, Integer pageSize);

    AnswerEntity get(Integer id);
}
