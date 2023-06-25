package com.example.service;


import com.example.exp.BadRequestException;
import com.example.exp.ItemNotFoundException;
import com.example.form.AnswerForm;
import com.example.model.dto.AnswerDto;
import com.example.model.dto.answer.AnswerCreateDto;
import com.example.model.dto.answer.AnswerFullInfoDto;
import com.example.model.entity.AnswerEntity;
import com.example.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public String create(AnswerForm dto) {
        AnswerEntity entity = new AnswerEntity();
        entity.setBody(dto.getBody());
        entity.setQuestionId(dto.getQuestionId());
        entity.setUserId(dto.getAccountDto().getId());
        entity.setAttachmentId(dto.getAttachmentId());
        entity.setState(true);
        entity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        answerRepository.save(entity);
        return "Successfully";
    }

    @Override
    public String update(AnswerForm dto, Integer answerId) {

        AnswerEntity entity = get(answerId);

        entity.setAttachmentId(dto.getAttachmentId());
        entity.setBody(dto.getBody());
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
    public Page<AnswerDto> getListForUser(Integer pagenumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pagenumber, pageSize);

        Page<AnswerEntity> pagination = answerRepository.pagination(pageable);
        List<AnswerDto> collect = pagination.stream().map(this::map).collect(Collectors.toList());
        return new PageImpl<>(collect, pageable, pagination.getTotalElements());
    }


    @Override
    public AnswerDto getAnswerById(Integer id) {
        Optional<AnswerEntity> entity = answerRepository.findById(id);
        return map(entity.get());
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
            dtoList.add(dto);
        });
        return new PageImpl(dtoList, pageable, pagination.getTotalElements());
    }


    private AnswerCreateDto entityToDto(AnswerEntity answerEntity) {
        AnswerCreateDto dto = new AnswerCreateDto();
        dto.setUserId(answerEntity.getUserId());
        dto.setBody(answerEntity.getBody());
        dto.setAttachmentId(answerEntity.getAttachmentId());
        dto.setQuestionId(answerEntity.getQuestionId());
        dto.setId(answerEntity.getId());
        return dto;
    }

    // MAPPER
    private AnswerDto map(AnswerEntity answerEntity) {
        AnswerDto dto = new AnswerDto();
        dto.setId(answerEntity.getId());
        dto.setUserId(answerEntity.getUserId());
        dto.setBody(answerEntity.getBody());
        dto.setAttachmentId(answerEntity.getAttachmentId());
        dto.setQuestionId(answerEntity.getQuestionId());
        dto.setId(answerEntity.getId());
        return dto;
    }

    @Override
    public AnswerEntity get(Integer id) {
        Optional<AnswerEntity> opt = answerRepository.findByIdAndStateIsTrue(id);
        if (opt.isEmpty()) {
            throw new ItemNotFoundException("Kalla bunaqa Answer yo'qku");
        }
        return opt.get();
    }


}
