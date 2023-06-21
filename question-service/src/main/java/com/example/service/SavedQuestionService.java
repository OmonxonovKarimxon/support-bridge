package com.example.service;

import org.springframework.stereotype.Service;

@Service
public interface SavedQuestionService {
    boolean add(int questionId);

    boolean remove(int questionId);
}
