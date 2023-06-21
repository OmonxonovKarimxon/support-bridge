package com.example.service;

import com.example.exp.ItemNotFoundException;
import com.example.model.dto.question.QuestionAddDTO;
import com.example.model.dto.question.QuestionEditDTO;
import com.example.model.dto.question.QuestionResponseDTO;
import com.example.model.entity.QuestionEntity;
import com.example.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

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

    @Override
    public int edit(QuestionEditDTO questionEditDTO) {

        return questionRepository.editByQuestionId(questionEditDTO.getNewDescription(),
                questionEditDTO.getNewTitle(), questionEditDTO.getQuestionId());
    }

    @Override
    public int delete(int id) {

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
        }

        return resList;
    }


    @Override
    public QuestionEntity get(Integer id) {
        Optional<QuestionEntity> opt = questionRepository.findByIdAndStateIsTrue(id);
        if (opt.isEmpty()) {
            throw new ItemNotFoundException("Something went wrong");
        }
        return opt.get();

    }
}
