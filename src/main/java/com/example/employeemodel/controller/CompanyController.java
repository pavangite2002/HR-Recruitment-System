package com.example.employeemodel.controller;

import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
 public class CompanyController {

    @Autowired
    private  CompanyService service;

    @PostMapping
    public CompanyDto create(@RequestBody CompanyDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public List<CompanyDto> getAll() {
        return service.getAll();
    }
}