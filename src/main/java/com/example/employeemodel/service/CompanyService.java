package com.example.employeemodel.service;

import com.example.employeemodel.dto.CompanyDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.*;
import java.util.Map;

@Service
public class CompanyService {
    Map<Long, CompanyDto> store = new HashMap<>();
    Long idCounter = 1L;
    public CompanyDto create(CompanyDto dto) {
        dto.setId(idCounter++);
        store.put(dto.getId(), dto);
        return dto;
    }
    public List<CompanyDto> getAll() {
        return new ArrayList<>(store.values());
    }
}

