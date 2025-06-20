package com.example.employeemodel.service.impl;

import com.example.employeemodel.dto.SubCategoryDto;
import com.example.employeemodel.dto.SubCategoryResponseDto;
import com.example.employeemodel.exception.BadRequestException;
import com.example.employeemodel.exception.ResourceNotFoundException;
import com.example.employeemodel.mapper.SubCategoryMapper;
import com.example.employeemodel.model.Department;
import com.example.employeemodel.model.SubCategory;
import com.example.employeemodel.repository.DepartmentRepository;
import com.example.employeemodel.repository.SubCategoryRepository;
import com.example.employeemodel.service.SubCategoryService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.employeemodel.helper.utils.UpdateUtils.getJsonNode;
import static com.example.employeemodel.helper.utils.UpdateUtils.readValue;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;
    private final DepartmentRepository departmentRepository;
    private final SubCategoryMapper subCategoryMapper;

    @Override
    public SubCategoryResponseDto create(SubCategoryDto dto) {
        SubCategory subCategory = subCategoryMapper.subCategoryDtoToSubCategory(dto);
        return subCategoryMapper.subCategoryToSubCategoryResponseDto(subCategoryRepository.save(subCategory));
    }

    @Override
    public List<SubCategoryResponseDto> getAll() {
        return subCategoryRepository.findAll()
                .stream()
                .map(subCategoryMapper::subCategoryToSubCategoryResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubCategoryResponseDto getById(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with ID: " + id));
        return subCategoryMapper.subCategoryToSubCategoryResponseDto(subCategory);
    }

    @Override
    public SubCategoryResponseDto update(Long id, String subCategoryDtoJson) {
        SubCategory existing = subCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with ID: " + id));

        SubCategoryDto updatedFields = readValue(subCategoryDtoJson, SubCategoryDto.class);
        JsonNode jsonNode = getJsonNode(subCategoryDtoJson);

        if (jsonNode.has("name")) {
            String name = updatedFields.getName();
            if (name == null || name.length() < 2 || name.length() > 100) {
                throw new BadRequestException("Name must be between 2 and 100 characters.");
            }
            existing.setName(name);
        }

        if (jsonNode.has("departmentId")) {
            Long departmentId = updatedFields.getDepartmentId();
            Department department = departmentRepository.findById(departmentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department ID not found : " + departmentId));
            existing.setDepartment(department);
        }

        return subCategoryMapper.subCategoryToSubCategoryResponseDto(subCategoryRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        SubCategory subCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with ID: " + id));
        subCategoryRepository.delete(subCategory);
    }
}
