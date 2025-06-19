package com.example.employeemodel.service;

import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.dto.CompanyResponseDto;
import com.example.employeemodel.model.Company;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface CompanyService {

    CompanyResponseDto create(CompanyDto dto);

    List<CompanyResponseDto> getAll();

    CompanyResponseDto getById(Long id);

    CompanyResponseDto update(Long id, String updatedDto) throws BadRequestException;

    void delete(Long id);
}
