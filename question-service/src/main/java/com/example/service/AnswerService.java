package com.example.service;

import com.example.model.dto.answer.AnswerCreateDto;
import com.example.model.dto.answer.AnswerFullInfoDto;
import com.example.model.dto.answer.AnswerUpdateDto;
import org.springframework.data.domain.Page;

public interface AnswerService {

    String create (AnswerCreateDto dto);
     String update (AnswerUpdateDto dto, Integer answerId);
     String delete (Integer id);
     Page<AnswerCreateDto> getListForUser (Integer pagenumber, Integer pageSize);
     AnswerCreateDto getAnswerById (Integer id);
    Page<AnswerFullInfoDto> getListForAdmin (Integer pagenumber, Integer pageSize);



}
