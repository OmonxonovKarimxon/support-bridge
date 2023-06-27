package com.company.service;

import com.company.dto.TagResponseDTO;
import com.company.form.AddForm;
import com.company.form.EditForm;

import java.util.List;

public interface TagService {

    TagResponseDTO add(AddForm addForm);

    String edit(EditForm editForm);

    String delete(int userId, int tagId);

    List<TagResponseDTO> getAll();

    TagResponseDTO getByName(String name);
}
