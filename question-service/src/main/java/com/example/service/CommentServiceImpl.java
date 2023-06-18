package com.example.service;


import com.example.enums.CommentType;
import com.example.exp.ItemNotFoundException;
import com.example.form.CommentForm;
import com.example.model.dto.comment.CommentCreateDto;
import com.example.model.dto.comment.CommentDto;
import com.example.model.dto.comment.CommentUpdateDto;
import com.example.model.entity.CommentEntity;
import com.example.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final AnswerService answerService;
    private final QuestionService questionService;

    @Override
    public String create(CommentForm dto) {
        CommentEntity entity = new CommentEntity();
        entity.setBody(dto.getBody());
        entity.setUserId(dto.getAccountDto().getId());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setCommentType(dto.getCommentTypes());
        entity.setOwnerId(dto.getOwnerId());
        entity.setState(true);
        commentRepository.save(entity);
        return "Successfully oka ";
    }

    @Override
    public String update(CommentForm dto, Integer commentId) {

        //TODO userId ni tekshirish kerak

        if (dto.getCommentTypes().equals(CommentType.ANSWER)) {
            answerService.get(dto.getOwnerId());
        } else {
            questionService.get(dto.getOwnerId());
        }

        Optional<CommentEntity> byId = commentRepository.findByIdAndStateIsTrue(commentId);

        if (byId.isPresent()) {
            CommentEntity entity = byId.get();
            entity.setBody(dto.getBody());
            entity.setUpdatedDate(LocalDateTime.now());

            commentRepository.save(entity);

            return "successfullly updated";
        }
        return "not found";
    }

    @Override
    public String delete(Integer id) {

        Optional<CommentEntity> byId = commentRepository.findByIdAndStateIsTrue(id);
        if (byId.isPresent()) {
            CommentEntity entity = byId.get();
            entity.setState(false);
            commentRepository.save(entity);
            return "deleted";
        }
        throw new ItemNotFoundException("Not found");
    }

    @Override
    public Page<CommentDto> getListForUser(Integer pagenumber, Integer pageSize) {

        Pageable pageable = PageRequest.of(pagenumber, pageSize);

        Page<CommentEntity> pagination = commentRepository.pagination(pageable);

        List<CommentDto> collect = pagination.stream().map(this::map).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, pagination.getTotalElements());
    }

    @Override
    public CommentDto getComment(Integer id) {
        return map(get(id));
    }

    // HELPER
    private CommentEntity get(Integer id) {
        Optional<CommentEntity> opt = commentRepository.findByIdAndStateIsTrue(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        throw new ItemNotFoundException("Not found");
    }

    // MAPPER
    private CommentDto map(CommentEntity entity) {
        CommentDto dto = new CommentDto();
        dto.setId(entity.getId());
        dto.setBody(entity.getBody());
        dto.setOwnerId(entity.getOwnerId());
        dto.setCommentType(entity.getCommentType());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}
