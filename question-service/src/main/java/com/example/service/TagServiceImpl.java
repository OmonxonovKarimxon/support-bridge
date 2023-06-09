package com.example.service;

import com.example.exp.BadRequestException;
import com.example.model.dto.tag.EditTagRequestDTO;
import com.example.model.dto.tag.TagRequestDTO;
import com.example.model.dto.tag.TagResponseDTO;
import com.example.model.entity.TagEntity;
import com.example.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public TagResponseDTO add(TagRequestDTO addTagRequestDTO) {

        TagEntity byName = tagRepository.findByName(addTagRequestDTO.getName());

        if (byName != null) {
            throw new BadRequestException("Tag is already exists");
        }
        TagEntity tag = tagRepository.save(
                TagEntity.builder()
                        .name(addTagRequestDTO.getName())
                        .build()
        );
        return TagResponseDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    @Override
    public Integer edit(EditTagRequestDTO editTagRequestDTO) {

        TagEntity byName = tagRepository.findByName(editTagRequestDTO.getName());

        if (byName != null) {
            throw new BadRequestException("Tag is already exists");
        }

        return tagRepository.editTagById(editTagRequestDTO.getName(), editTagRequestDTO.getOldTagId());
    }

    @Override
    public Integer delete(int tagId) {
        return tagRepository.deleteTagById(tagId);
    }

    @Override
    public List<TagResponseDTO> getAll() {

        List<TagEntity> allByState = tagRepository.getList();
        List<TagResponseDTO> responseDTOList = new ArrayList<>();

        for (TagEntity tag : allByState) {

            TagResponseDTO responseDTO = TagResponseDTO.builder()
                    .id(tag.getId())
                    .name(tag.getName())
                    .build();

            responseDTOList.add(responseDTO);
        }

        return responseDTOList;
    }

    @Override
    public TagResponseDTO getByName(String name) {

        TagEntity byName = tagRepository.findByName(name);


        return TagResponseDTO.builder().id(byName.getId()).name(byName.getName()).build();
    }


}
