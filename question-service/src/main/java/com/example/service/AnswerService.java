package com.example.service;

import com.example.model.dto.answer.AnswerCreateDto;
import com.example.model.dto.answer.AnswerFullInfoDto;
import com.example.model.dto.answer.AnswerUpdateDto;
import com.example.model.entity.AnswerEntity;
import org.springframework.data.domain.Page;

public interface AnswerService {

    String create (AnswerCreateDto dto);
     String update (AnswerUpdateDto dto, Integer answerId);
     String delete (Integer id);
     Page<AnswerUpdateDto> getListForUser (Integer pagenumber, Integer pageSize);
    AnswerUpdateDto getAnswerById (Integer id);
    Page<AnswerFullInfoDto> getListForAdmin (Integer pagenumber, Integer pageSize);

    AnswerEntity get(Integer id);
}
