package com.company.proxy;

import com.company.dto.QuestionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "question-service")
public interface QuestionProxy {

    @GetMapping("/question/pub/get/{questionId}")
    ResponseEntity<QuestionDto> getQuestion(@PathVariable("questionId") Integer questionId);



}
