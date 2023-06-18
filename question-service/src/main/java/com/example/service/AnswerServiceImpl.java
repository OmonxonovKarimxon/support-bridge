package com.example.service;


import com.example.exp.BadRequestException;
import com.example.exp.ItemNotFoundException;
import com.example.model.dto.answer.AnswerCreateDto;
import com.example.model.dto.answer.AnswerFullInfoDto;
import com.example.model.dto.answer.AnswerUpdateDto;
import com.example.model.entity.AnswerEntity;
import com.example.repository.AnswerRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;


    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    @Override
    public String create(AnswerCreateDto dto) {
        AnswerEntity entity = new AnswerEntity();
        entity.setBody(dto.getBody());
        entity.setQuestionId(dto.getQuestionId());
        entity.setUserId(dto.getUserId());
        entity.setAttachmentId(dto.getAttachmentId());
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        answerRepository.save(entity);
        return "Successfully";
    }

    @Override
    public String update(AnswerUpdateDto dto, Integer answerId) {

        AnswerEntity entity = get(answerId);

            entity.setAttachmentId(dto.getAttachmentId());
            entity.setBody(dto.getBody());
            entity.setUserId(dto.getUserId());
            entity.setQuestionId(dto.getQuestionId());
            entity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
            answerRepository.save(entity);

            return "successfullly updated";
    }

    @Override
    public String delete(Integer answerId) {

        Optional<AnswerEntity> byId = answerRepository.findById(answerId);
        AnswerEntity entity = answerRepository.findById(answerId).get();

        if (!byId.isPresent() && entity.getState()) {
            throw new BadRequestException("answer not found");
        }

        answerRepository.deleteByAnswerId(answerId);

        return "deleted";
    }

    @Override
    public Page<AnswerUpdateDto> getListForUser(Integer pagenumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pagenumber, pageSize);

        Page<AnswerEntity> pagination = answerRepository.pagination(pageable);
        List<AnswerUpdateDto> dtoList = new LinkedList<>();
        pagination.getContent().forEach(answerEntity -> {
            dtoList.add(entityToDto(answerEntity));
        });
        return new PageImpl<>(dtoList, pageable, pagination.getTotalElements());
    }


    @Override
    public AnswerUpdateDto getAnswerById(Integer id) {
        Optional<AnswerEntity> entity = answerRepository.findById(id);
        return entityToDto(entity.get());
    }

    @Override
    public Page<AnswerFullInfoDto> getListForAdmin(Integer pagenumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pagenumber, pageSize);

        Page<AnswerEntity> pagination = answerRepository.pagination(pageable);
        List<AnswerFullInfoDto> dtoList = new LinkedList<>();
        pagination.getContent().forEach(answerEntity -> {
            AnswerFullInfoDto dto = new AnswerFullInfoDto();
            dto.setUserId(answerEntity.getUserId());
            dto.setBody(answerEntity.getBody());
            dto.setAttachmentId(answerEntity.getAttachmentId());
            dto.setQuestionId(answerEntity.getQuestionId());
            dto.setId(answerEntity.getId());
            dto.setCreatedDate(String.valueOf(answerEntity.getCreatedDate()));
            dto.setUpdatedDate(String.valueOf(answerEntity.getUpdatedDate()));
            dto.setState(answerEntity.getState());
            dtoList.add(dto);
        });
        return new PageImpl(dtoList, pageable, pagination.getTotalElements());
    }


    private AnswerUpdateDto entityToDto(AnswerEntity answerEntity) {
        AnswerUpdateDto dto = new AnswerUpdateDto();
        dto.setUserId(answerEntity.getUserId());
        dto.setBody(answerEntity.getBody());
        dto.setAttachmentId(answerEntity.getAttachmentId());
        dto.setQuestionId(answerEntity.getQuestionId());
        dto.setId(answerEntity.getId());
        return dto;
    }

    @Override
    public AnswerEntity get(Integer id){
        Optional<AnswerEntity> opt = answerRepository.findByIdAndStateIsTrue(id);
        if (opt.isEmpty()){
            throw new ItemNotFoundException("Kalla bunaqa Answer yo'qku");
        }
        return opt.get();
    }

}
