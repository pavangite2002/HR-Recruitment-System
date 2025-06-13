package com.example.employeemodel.service;

import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.dto.CompanyResponseDto;
import com.example.employeemodel.model.Company;

import java.util.List;

public interface CompanyService {

    CompanyResponseDto create(CompanyDto dto);

    List<CompanyResponseDto> getAll();

    CompanyResponseDto getById(Long id);

    CompanyResponseDto update(Long id, String updatedDto);

    void delete(Long id);
}
