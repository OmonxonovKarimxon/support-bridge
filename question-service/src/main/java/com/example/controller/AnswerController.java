package com.example.controller;


import com.example.form.AnswerForm;
import com.example.model.dto.AnswerDto;
import com.example.model.dto.answer.AnswerFullInfoDto;
import com.example.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AnswerForm dto) {

        String response = answerService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{answerId}")
    public ResponseEntity<String> create(@PathVariable("answerId") Integer answerId,
                                         @RequestBody AnswerForm dto) {

        String response = answerService.update(dto, answerId);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/paging")
    public ResponseEntity<?> reportList(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {

        Page<AnswerDto> list = answerService.getListForUser(page, size);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/{answerId}")
    public ResponseEntity<?> deleteById(@PathVariable("answerId") Integer answerId) {

        String response = answerService.delete(answerId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{AnswerId}")
    public ResponseEntity<?> getByUserId(@PathVariable("answerId") Integer answerId) {

        AnswerDto dto = answerService.getAnswerById(answerId);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/admin_paging")
    public ResponseEntity<?> answerFullInfoPagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {

        Page<AnswerFullInfoDto> list = answerService.getListForAdmin(page, size);
        return ResponseEntity.ok().body(list);
    }

}