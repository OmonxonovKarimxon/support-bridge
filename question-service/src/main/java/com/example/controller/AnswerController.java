package com.example.controller;


import com.example.model.dto.answer.AnswerCreateDto;
import com.example.model.dto.answer.AnswerFullInfoDto;
import com.example.model.dto.answer.AnswerUpdateDto;
import com.example.service.AnswerService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {
    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody AnswerCreateDto dto) {

        String response = answerService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{answerId}")
    public ResponseEntity<String> create(@PathVariable("answerId") Integer answerId,
                                         @RequestBody AnswerUpdateDto dto) {

        String response = answerService.update(dto, answerId);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/answerList")
    public ResponseEntity<?> reportList(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {

        Page<AnswerUpdateDto> list = answerService.getListForUser(page, size);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/deleteById/{answerId}")
    public ResponseEntity<?> deleteById(@PathVariable("answerId") Integer answerId) {

        String response = answerService.delete(answerId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getAnswerById/{AnswerId}")
    public ResponseEntity<?> getByUserId(@PathVariable("answerId") Integer answerId) {

        AnswerUpdateDto dto = answerService.getAnswerById(answerId);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/paginationAdmin")
    public ResponseEntity<?> answerFullInfoPagination(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {

        Page<AnswerFullInfoDto> list = answerService.getListForAdmin(page, size);
        return ResponseEntity.ok().body(list);
    }

}