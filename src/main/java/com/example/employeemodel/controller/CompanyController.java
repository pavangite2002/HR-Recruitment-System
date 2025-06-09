package com.example.employeemodel.controller;

import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    // Create company
    @PostMapping
    public ResponseEntity<CompanyDto> createCompany(@RequestBody CompanyDto companyDto) {
        CompanyDto createdCompany = companyService.create(companyDto);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED); // 201 status
    }

    // Get all
    @GetMapping
    public ResponseEntity<List<CompanyDto>> getAllCompanies() {
        List<CompanyDto> companies = companyService.getAll();
        return ResponseEntity.ok(companies); // 200 status
    }

    // Get a company bu id
    @GetMapping("/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable Long id) {
        CompanyDto company = companyService.getById(id);
        return ResponseEntity.ok(company); // 200 status
    }

    // Update a company by id
    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> updateCompany(@PathVariable Long id, @RequestBody CompanyDto dto) {
        CompanyDto updatedCompany = companyService.update(id, dto);
        return ResponseEntity.ok(updatedCompany); // 200 status
    }

    // Delete company
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
