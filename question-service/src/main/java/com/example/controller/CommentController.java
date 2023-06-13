package com.example.controller;


import com.example.model.dto.answer.AnswerCreateDto;
import com.example.model.dto.comment.CommentCreateDto;
import com.example.model.dto.comment.CommentFullInfoDto;
import com.example.model.dto.comment.CommentUpdateDto;
import com.example.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CommentCreateDto dto) {

        String response = commentService.create(dto);
        return ResponseEntity.ok().body(response);
    }

//    @PutMapping("/update/{answerId}")
//    public ResponseEntity<String> create(@PathVariable("answerId") Integer answerId,
//                                         @RequestBody CommentUpdateDto dto) {
//
//        String response = commentService.update(dto, answerId);
//        return ResponseEntity.ok().body(response);
//    }


//    @GetMapping("/answerList")
//    public ResponseEntity<?> reportList(@RequestParam(value = "page", defaultValue = "0") int page,
//                                        @RequestParam(value = "size", defaultValue = "5") int size) {
//
//        Page<CommentCreateDto> list = commentService.getListForUser(page, size);
//        return ResponseEntity.ok().body(list);
//    }
//
//    @DeleteMapping("/deleteById/{answerId}")
//    public ResponseEntity<?> deleteById(@PathVariable("answerId") Integer answerId) {
//
//        String response = commentService.delete(answerId);
//        return ResponseEntity.ok().body(response);
//    }
//
//    @GetMapping("/getAnswerById/{AnswerId}")
//    public ResponseEntity<?> getByUserId(@PathVariable("answerId") Integer answerId) {
//
//        AnswerCreateDto dto = commentService.getAnswerById(answerId);
//        return ResponseEntity.ok().body(dto);
//    }
//
//    @GetMapping("/paginationAdmin")
//    public ResponseEntity<?> answerFullInfoPagination(@RequestParam(value = "page", defaultValue = "0") int page,
//                                        @RequestParam(value = "size", defaultValue = "5") int size) {
//
//        Page<CommentFullInfoDto> list = commentService.getListForAdmin(page, size);
//        return ResponseEntity.ok().body(list);
//    }

}