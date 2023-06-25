package com.company.service;


import com.company.dto.QuestionDto;
import com.company.enums.CommentType;
import com.company.exp.ItemNotFoundException;
import com.company.form.CommentForm;
import com.company.model.CommentDto;
import com.company.model.entity.CommentEntity;
import com.company.proxy.QuestionProxy;
import com.company.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final QuestionProxy questionProxy;

    @Override
    public String create(CommentForm dto) {

        if (dto.getCommentTypes().equals(CommentType.QUESTION)) {

            ResponseEntity<QuestionDto> question = questionProxy.getQuestion(dto.getOwnerId());
            if(question.getBody()==null){
                throw new ItemNotFoundException("bunaqa question yoq bizda tvar");
            }
        } else {
            //            questionProxy.getQuestion(dto.getOwnerId());
        }


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

//        if (dto.getCommentTypes().equals(CommentType.ANSWER)) {
//            answerService.get(dto.getOwnerId());
//        } else {
//           questionService.getById(dto.getOwnerId());
//        }

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
