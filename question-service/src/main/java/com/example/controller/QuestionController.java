package com.example.controller;


import com.example.dto.PagingDto;
import com.example.dto.QuestionDto;
import com.example.dto.QuestionParamsDto;
import com.example.dto.UserAccountDto;
import com.example.exp.ItemNotFoundException;
import com.example.form.QuestionForm;
import com.example.model.entity.QuestionEntity;
import com.example.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    private void checkHas(Integer questionId, Integer userId) {
        QuestionEntity question = questionService.get(questionId, userId);

        if (question == null) {
            throw new ItemNotFoundException("Question not found");
        }
    }

    @GetMapping("/pub/paging")
    public ResponseEntity<PagingDto<QuestionDto>> paging(@RequestBody QuestionParamsDto params) {

        return ResponseEntity.ok().body(questionService.getByParams(params));
    }

    @PostMapping
    public ResponseEntity<QuestionDto> add(@RequestBody QuestionForm form, Authentication authentication) {
        form.setAccount((UserAccountDto) authentication.getDetails());

        QuestionDto responseDTO = questionService.add(form);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/{question_id}")
    public ResponseEntity<?> edit(@RequestBody QuestionForm form, @PathVariable Integer question_id, Authentication authentication) {

        form.setAccount((UserAccountDto) authentication.getDetails());

        checkHas(question_id, form.getAccount().getId());

        QuestionDto response = questionService.edit(form);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity<?> delete(@PathVariable Integer questionId, Authentication authentication) {

        UserAccountDto account = (UserAccountDto) authentication.getDetails();

        checkHas(questionId, account.getId());

        questionService.delete(questionId);

        return ResponseEntity.ok("Deleted");
    }

}
