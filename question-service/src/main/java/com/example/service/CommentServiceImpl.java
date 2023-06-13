package com.example.service;


import com.example.enums.CommentType;
import com.example.model.dto.comment.CommentCreateDto;
import com.example.model.dto.comment.CommentFullInfoDto;
import com.example.model.dto.comment.CommentUpdateDto;
import com.example.model.entity.CommentEntity;
import com.example.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;


@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public String create(CommentCreateDto dto) {
        CommentEntity entity = new CommentEntity();
        entity.setBody(dto.getBody());
        entity.setQuestionId(dto.getQuestionId());
        entity.setUserId(dto.getUserId());
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        if(dto.getType().equals(CommentType.QUESTION)){
            entity.setCommentType(dto.getType());
        }else {
            entity.setCommentType(dto.getType());
        }

        commentRepository.save(entity);
        return "Successfully";
    }

    @Override
    public String update(CommentUpdateDto dto, Integer answerId) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public Page<CommentUpdateDto> getListForUser(Integer pagenumber, Integer pageSize) {
        return null;
    }

    @Override
    public CommentUpdateDto getAnswerById(Integer id) {
        return null;
    }

    @Override
    public Page<CommentFullInfoDto> getListForAdmin(Integer pagenumber, Integer pageSize) {
        return null;
    }
}
