package com.company.service;


import com.company.form.CommentForm;
import com.company.model.CommentDto;
import org.springframework.data.domain.Page;

public interface CommentService {

    String create(CommentForm dto);

    String update(CommentForm dto, Integer commentId);

    String delete(Integer id);

    Page<CommentDto> getListForUser(Integer pagenumber, Integer pageSize);

    CommentDto getComment(Integer id);
}
