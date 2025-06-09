package com.example.employeemodel.service;

import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.dto.DeptDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {

    private final Map<Long, CompanyDto> store = new HashMap<>();
    private Long idCounter = 1L;

    public CompanyDto create(CompanyDto dto) {
        dto.setId(idCounter++);

        if (dto.getDepartments() != null) {
            for (DeptDto dept : dto.getDepartments()) {
                dept.setCompanyId(dto.getId());

                if (dept.getSubCategories() == null) {
                    dept.setSubCategories(new ArrayList<>());
                }
            }
        }

        store.put(dto.getId(), dto);
        return dto;
    }

    public List<CompanyDto> getAll() {
        return new ArrayList<>(store.values());
    }

    public CompanyDto getById(Long id) {
        CompanyDto dto = store.get(id);
        if (dto == null) {
            throw new NoSuchElementException("Company not found with ID: " + id);
        }
        return dto;
    }

    public CompanyDto update(Long id, CompanyDto updatedDto) {
        CompanyDto existing = store.get(id);
        if (existing == null) {
            throw new NoSuchElementException("Cannot update. Company not found with ID: " + id);
        }
        updatedDto.setId(id);

        if (updatedDto.getDepartments() != null) {
            for (DeptDto dept : updatedDto.getDepartments()) {
                dept.setCompanyId(id);
                if (dept.getSubCategories() == null) {
                    dept.setSubCategories(new ArrayList<>());
                }
            }
        }

        store.put(id, updatedDto);
        return updatedDto;
    }

    public void delete(Long id) {
        if (!store.containsKey(id)) {
            throw new NoSuchElementException("Cannot delete. Company not found with ID: " + id);
        }
        store.remove(id);
    }
}
