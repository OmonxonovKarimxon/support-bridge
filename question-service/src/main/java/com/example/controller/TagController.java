package com.example.controller;

import com.example.model.dto.tag.EditTagRequestDTO;
import com.example.model.dto.tag.TagRequestDTO;
import com.example.model.dto.tag.TagResponseDTO;
import com.example.service.TagServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagServiceImpl tagService;


    @PostMapping("/add")
    public ResponseEntity<TagResponseDTO> add(@RequestBody TagRequestDTO addTagRequestDTO) {

        TagResponseDTO tag = tagService.add(addTagRequestDTO);
        return ResponseEntity.ok().body(tag);
    }

    @PutMapping("/update")
    public ResponseEntity<?> edit(
            @RequestBody EditTagRequestDTO editTagRequestDTO
    ) {

        Integer res = tagService.edit(editTagRequestDTO);

        String message = res == 0 ? "failed" : "success";

        return ResponseEntity.ok(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") int tagId
    ) {

        Integer res = tagService.delete(tagId);

        String message = res == 0 ? "failed" : "success";

        return ResponseEntity.ok(message);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TagResponseDTO>> getAll() {

        List<TagResponseDTO> list = tagService.getAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/get/{name}")
    public ResponseEntity<TagResponseDTO> get(
            @PathVariable("name") String name
    ) {

        TagResponseDTO tagResponseDTO = tagService.getByName(name);

        return ResponseEntity.ok().body(tagResponseDTO);
    }

}
