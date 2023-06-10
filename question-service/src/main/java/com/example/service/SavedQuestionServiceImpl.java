package com.example.service;

import com.example.model.entity.SavedQuestionEntity;
import com.example.repository.SavedQuestionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class SavedQuestionServiceImpl implements SavedQuestionService {

    private final SavedQuestionRepository savedQuestionRepository;

    @Override
    public boolean add(int questionId) {

        Optional<SavedQuestionEntity> isExists
                = savedQuestionRepository.findByQuestionIdAndUserId(questionId, 1);

        if (isExists.isPresent()) {
            return true;
        }


        SavedQuestionEntity savedQuestion = savedQuestionRepository.save(
                SavedQuestionEntity.builder()
                        .questionId(questionId)
                        .userId(1)
                        .build()
        );

        return savedQuestion != null;
    }

    @Override
    public boolean remove(int questionId) {

        int removed = savedQuestionRepository.deleteByQuestionIdAndUserId(questionId, 1);


        return removed > 0;
    }
}
