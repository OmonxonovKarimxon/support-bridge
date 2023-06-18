package com.example.controller;


import com.example.model.dto.answer.AnswerCreateDto;
import com.example.model.dto.comment.CommentCreateDto;
import com.example.model.dto.comment.CommentDto;
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


    @PostMapping
    public ResponseEntity<String> create(@RequestBody CommentCreateDto dto) {

        String response = commentService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> update(@PathVariable("commentId") Integer commentId,
                                         @RequestBody CommentUpdateDto dto) {

        String response = commentService.update(dto, commentId);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/commentlist")
    public ResponseEntity<?> reportList(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "5") int size) {

        Page<CommentDto> list = commentService.getListForUser(page, size);
        return ResponseEntity.ok().body(list);
    }
//
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteById(@PathVariable("commentId") Integer commentId) {

        String response = commentService.delete(commentId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<?> getByUserId(@PathVariable("commentId") Integer commentId) {

        return ResponseEntity.ok(commentService.getComment(commentId));
    }
//
//    @GetMapping("/paginationAdmin")
//    public ResponseEntity<?> answerFullInfoPagination(@RequestParam(value = "page", defaultValue = "0") int page,
//                                        @RequestParam(value = "size", defaultValue = "5") int size) {
//
//        Page<CommentFullInfoDto> list = commentService.getListForAdmin(page, size);
//        return ResponseEntity.ok().body(list);
//    }

}