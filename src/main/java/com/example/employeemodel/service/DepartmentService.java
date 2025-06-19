package com.example.employeemodel.service;


import com.example.employeemodel.dto.DepartmentDto;
import com.example.employeemodel.dto.DepartmentResponseDto;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDto create(DepartmentDto dto);

    List<DepartmentResponseDto> getAll();

    DepartmentResponseDto getById(Long id);

    DepartmentResponseDto update(Long id, String dtoJson);

    void delete(Long id);
}

