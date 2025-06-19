package com.example.employeemodel.service.impl;


import com.example.employeemodel.dto.DepartmentDto;
import com.example.employeemodel.dto.DepartmentResponseDto;
import com.example.employeemodel.mapper.DepartmentMapper;
import com.example.employeemodel.model.Company;
import com.example.employeemodel.model.Department;
import com.example.employeemodel.repository.CompanyRepository;
import com.example.employeemodel.repository.DepartmentRepository;
import com.example.employeemodel.service.DepartmentService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.employeemodel.util.UpdateUtils.getJsonNode;
import static com.example.employeemodel.util.UpdateUtils.readValue;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDto create(DepartmentDto dto) {
        Department department = departmentMapper.deptDtoToDepartment(dto);
        return departmentMapper.departmentToDepartmentDto(departmentRepository.save(department));
    }

    @Override
    public List<DepartmentResponseDto> getAll() {
        return departmentRepository.findAll().stream().map(departmentMapper::departmentToDepartmentDto).collect(Collectors.toList());
    }

    @Override
    public DepartmentResponseDto getById(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Department not found with ID: " + id));
        return departmentMapper.departmentToDepartmentDto(department);
    }

    @Override
    public DepartmentResponseDto update(Long id, String dtoJson) {
        Department existing = departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Department not found with ID: " + id));
        DepartmentDto updatedFields = readValue(dtoJson, DepartmentDto.class);
        JsonNode jsonNode = getJsonNode(dtoJson);
        if (jsonNode.has("name")) {
            String name = updatedFields.getName();
            if (name.length() < 2 || name.length() > 100) {
                throw new RuntimeException("Name must be between 2 and 100 characters.");
            }
            existing.setName(name);
        }
        if (jsonNode.has("description")) {
            String desc = updatedFields.getDescription();
            if (desc != null && (desc.length() < 5 || desc.length() > 255)) {
                throw new RuntimeException("Description must be between 5 and 255 characters.");
            }
            existing.setDescription(desc);
        }
        if (jsonNode.has("companyId")) {
            Long companyId = updatedFields.getCompanyId();
            Company company = companyRepository.findById(companyId).orElseThrow(() -> new RuntimeException("Company Id not Found....."));
            existing.setCompany(company);
        }
        return departmentMapper.departmentToDepartmentDto(departmentRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Department not found with ID: " + id));
        department.getSubCategories().clear();
        department.getEmployees().clear();
        departmentRepository.delete(department);
    }
}

