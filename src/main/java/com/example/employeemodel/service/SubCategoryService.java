package com.example.employeemodel.service;

import com.example.employeemodel.dto.SubCategoryDto;
import com.example.employeemodel.dto.SubCategoryResponseDto;
import jakarta.validation.Valid;

import java.util.List;

public interface SubCategoryService {
    SubCategoryResponseDto create(@Valid SubCategoryDto dto);

    List<SubCategoryResponseDto> getAll();

    SubCategoryResponseDto getById(Long id);

    SubCategoryResponseDto update(Long id, String subCategoryDtoJson);

    void delete(Long id);
}
