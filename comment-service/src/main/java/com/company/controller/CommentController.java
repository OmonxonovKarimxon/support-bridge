package com.company.controller;

import com.company.dto.UserAccountDto;
import com.company.form.CommentForm;
import com.company.model.CommentDto;
import com.company.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @PostMapping
    public ResponseEntity<String> create(@RequestBody CommentForm dto, Authentication authentication) {
        UserAccountDto userAccount = (UserAccountDto) authentication.getDetails();
        dto.setAccountDto(userAccount);
        String response = commentService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<String> update(@PathVariable("commentId") Integer commentId,
                                         @RequestBody CommentForm dto,
                                         Authentication authentication) {
        UserAccountDto details = (UserAccountDto) authentication.getDetails();
        dto.setAccountDto(details);

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
}
