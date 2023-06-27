package com.company.controller;


import com.company.dto.UserAccountDto;
import com.company.form.AddForm;
import com.company.dto.TagResponseDTO;
import com.company.form.EditForm;
import com.company.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagService tagService;


    @PostMapping
    public ResponseEntity<TagResponseDTO> add(@RequestBody AddForm addForm, Authentication authentication) {
        UserAccountDto details = (UserAccountDto) authentication.getDetails();
        addForm.setAccountDto(details);
        TagResponseDTO tag = tagService.add(addForm);
        return ResponseEntity.ok().body(tag);
    }

    @PutMapping
    public ResponseEntity<?> edit(
            @RequestBody EditForm editForm, Authentication authentication
    ) {
        UserAccountDto details = (UserAccountDto) authentication.getDetails();
        editForm.setUserAccountDto(details);
        String response = tagService.edit(editForm);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int tagId, Authentication authentication
    ) {
        UserAccountDto details = (UserAccountDto) authentication.getDetails();

        String message = tagService.delete(details.getId(), tagId);

        return ResponseEntity.ok(message);
    }

    @GetMapping("/pub/list")
    public ResponseEntity<List<TagResponseDTO>> getAll() {

        List<TagResponseDTO> list = tagService.getAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/pub/{name}")
    public ResponseEntity<TagResponseDTO> get(
            @PathVariable("name") String name
    ) {

        TagResponseDTO tagResponseDTO = tagService.getByName(name);

        return ResponseEntity.ok().body(tagResponseDTO);
    }

    //get by id

}
