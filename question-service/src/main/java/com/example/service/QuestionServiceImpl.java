package com.example.service;

<<<<<<< HEAD
import com.example.model.dto.question.QuestionAddDTO;
import com.example.model.dto.question.QuestionEditDTO;
import com.example.model.dto.question.QuestionResponseDTO;
import com.example.model.dto.user.UserAccountDto;
=======
import com.example.dto.PagingDto;
import com.example.dto.QuestionDto;
import com.example.dto.QuestionParamsDto;
import com.example.exp.ItemNotFoundException;
import com.example.form.QuestionForm;
>>>>>>> origin/coder
import com.example.model.entity.QuestionEntity;
import com.example.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
<<<<<<< HEAD
    private final AuthProxy authProxy;

    @Override
    public QuestionResponseDTO add(QuestionAddDTO questionAddDTO) {

        QuestionEntity question = questionRepository.save(QuestionEntity.builder()
                .ownerId(1)
                .description(questionAddDTO.getDescription())
                .title(questionAddDTO.getTitle())
                .build());

        return QuestionResponseDTO.builder()
                .id(question.getId())
                .created_at(question.getCreatedAt())
                .title(question.getTitle())
                .ownerId(question.getOwnerId())
                .description(question.getDescription())
                .build();
    }
=======
>>>>>>> origin/coder

    @Override
    public PagingDto<QuestionDto> getByParams(QuestionParamsDto params) {

        List<Sort.Order> orders = new ArrayList<>();

        Specification<QuestionEntity> notDeleted = (root, cq, cb) -> cb.isFalse(root.get("state"));

<<<<<<< HEAD
        return questionRepository.deleteByQuestionId(id);
    }

    @Override
    public List<QuestionResponseDTO> getAll() {

        List<QuestionEntity> all = questionRepository.getAll();
        List<QuestionResponseDTO> resList = new ArrayList<>();

        for (QuestionEntity question : all) {

            resList.add(QuestionResponseDTO.builder()
                    .description(question.getDescription())
                    .ownerId(question.getOwnerId())
                    .title(question.getTitle())
                    .created_at(question.getCreatedAt())
                    .id(question.getId())
                    .build());
=======
        if (params.getSort().equals("dateAsc")) {
            orders.add(Sort.Order.asc("createdAt"));
        }
        if (params.getSort().equals("dateDesc")) {
            orders.add(Sort.Order.desc("createdAt"));
>>>>>>> origin/coder
        }

        Specification<QuestionEntity> spec = where(notDeleted);
        Page<QuestionEntity> questions = questionRepository.findAll(spec, PageRequest.of(params.getPage() - 1, params.getSize(), Sort.by(orders)));

        // create Paging
        PagingDto<QuestionDto> paging = new PagingDto<>((int) questions.getTotalElements(), params.getPage(), params.getSize());

        paging.setItems(questions.stream().map(this::map).collect(Collectors.toList()));

        return paging;
    }

    @Override
    public QuestionDto add(QuestionForm form) {

        QuestionEntity question = questionRepository.save(QuestionEntity.builder()
                .ownerId(form.getAccount().getId())
                .description(form.getDescription())
                .title(form.getTitle())
                .state(true)
                .build());

        return map(question);
    }

    @Override
    public QuestionDto edit(QuestionForm form) {

        QuestionEntity entity = new QuestionEntity();
        entity.setTitle(form.getTitle());
        entity.setDescription(form.getDescription());

        questionRepository.save(entity);

        return map(entity);
    }

    @Override
    public void delete(Integer id) {
        questionRepository.deleteByQuestionId(id);
    }


    @Override
    public QuestionEntity get(Integer questionId, Integer userId) {
        Optional<QuestionEntity> opt = questionRepository.findByIdAndOwnerIdAndStateIsTrue(questionId, userId);
        if (opt.isEmpty()) {
            throw new ItemNotFoundException("Something went wrong");
        }
        return opt.get();

    }

    @Override
    public QuestionEntity getById(Integer questionId) {
        Optional<QuestionEntity> opt = questionRepository.findByIdAndStateIsTrue(questionId);
        if (opt.isEmpty()) {
            throw new ItemNotFoundException("Something went wrong");
        }
        return opt.get();
    }


    // MAPPER
    private QuestionDto map(QuestionEntity entity) {
        QuestionDto dto = new QuestionDto();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setOwnerId(entity.getOwnerId());
        return dto;
    }
}
