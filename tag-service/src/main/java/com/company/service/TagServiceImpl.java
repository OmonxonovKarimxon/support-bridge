package com.company.service;

import com.company.dto.TagResponseDTO;
import com.company.entity.TagEntity;
import com.company.exp.BadRequestException;
import com.company.exp.ItemNotFoundException;
import com.company.form.AddForm;
import com.company.form.EditForm;
import com.company.repository.TagRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public TagResponseDTO add(AddForm addForm) {

        TagEntity byName = tagRepository.findByName(addForm.getName());

        if (byName != null) {
            throw new BadRequestException("Tag is already exists");
        }
        TagEntity tag = tagRepository.save(
                TagEntity.builder()
                        .name(addForm.getName())
                        .createdBy(addForm.getAccountDto().getId())
                        .build()
        );
        return TagResponseDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .createdBy(tag.getCreatedBy())
                .build();
    }

    @Override
    public String edit(EditForm editForm) {

        TagEntity byName = tagRepository.findByName(editForm.getName());

        if (byName != null) {
            throw new BadRequestException("Tag is already exists");
        }
        Integer result = tagRepository.editTagById(editForm.getName(), editForm.getUserAccountDto().getId(), editForm.getOldTagId());
        if (result > 0) {

            return "success";
        }

        return "failed";
    }

    @Override
    public String delete(int userId, int tagId) {
        Integer result = tagRepository.deleteTagById(userId, tagId);

        if (result > 0)
            return "success";

        return "failed";
    }

    @Override
    public List<TagResponseDTO> getAll() {

        List<TagEntity> allByState = tagRepository.getList();
        List<TagResponseDTO> responseDTOList = new ArrayList<>();

        for (TagEntity tag : allByState) {

            TagResponseDTO responseDTO = TagResponseDTO.builder()
                    .id(tag.getId())
                    .name(tag.getName())
                    .createdBy(tag.getCreatedBy())
                    .build();

            responseDTOList.add(responseDTO);
        }

        return responseDTOList;
    }

    @Override
    public TagResponseDTO getByName(String name) {

        TagEntity byName = tagRepository.findByName(name);

        if (byName == null) {
            throw new ItemNotFoundException("tag is not exists");
        }

        return TagResponseDTO.builder().id(byName.getId()).name(byName.getName()).build();
    }


}
