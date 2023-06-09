package com.example.service;

import com.example.model.dto.tag.EditTagRequestDTO;
import com.example.model.dto.tag.TagRequestDTO;
import com.example.model.dto.tag.TagResponseDTO;

import java.util.List;

public interface TagService {

    TagResponseDTO add(TagRequestDTO addTagRequestDTO);

    Integer edit(EditTagRequestDTO editTagRequestDTO);

    Integer delete(int tagId);

    List<TagResponseDTO> getAll();

    TagResponseDTO getByName(String name);
}
