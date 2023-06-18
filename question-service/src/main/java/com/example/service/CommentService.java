package com.example.service;

import com.example.model.dto.comment.CommentCreateDto;
import com.example.model.dto.comment.CommentDto;
import com.example.model.dto.comment.CommentUpdateDto;
import org.springframework.data.domain.Page;

public interface CommentService {

    String create(CommentCreateDto dto);

    String update(CommentUpdateDto dto, Integer commentId);

    String delete(Integer id);

    Page<CommentDto> getListForUser(Integer pagenumber, Integer pageSize);

    CommentDto getComment(Integer id);
}
