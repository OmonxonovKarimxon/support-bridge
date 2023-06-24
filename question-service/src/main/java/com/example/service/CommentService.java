package com.example.service;

import com.example.form.CommentForm;
import com.example.model.dto.comment.CommentCreateDto;
import com.example.model.dto.comment.CommentDto;
import com.example.model.dto.comment.CommentUpdateDto;
import org.springframework.data.domain.Page;

public interface CommentService {

    String create(CommentForm dto);

    String update(CommentForm dto, Integer commentId);

    String delete(Integer id);

    Page<CommentDto> getListForUser(Integer pagenumber, Integer pageSize);

    CommentDto getComment(Integer id);
}
