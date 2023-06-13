package com.example.service;

import com.example.model.dto.comment.CommentCreateDto;
import com.example.model.dto.comment.CommentFullInfoDto;
import com.example.model.dto.comment.CommentUpdateDto;
import org.springframework.data.domain.Page;

public interface CommentService {

    String create (CommentCreateDto dto);
     String update (CommentUpdateDto dto, Integer answerId);
     String delete (Integer id);
     Page<CommentUpdateDto> getListForUser (Integer pagenumber, Integer pageSize);
    CommentUpdateDto getAnswerById (Integer id);
    Page<CommentFullInfoDto> getListForAdmin (Integer pagenumber, Integer pageSize);



}
