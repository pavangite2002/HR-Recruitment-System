package com.example.employeemodel.controller;

import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.dto.CompanyResponseDto;
import com.example.employeemodel.model.Company;
import com.example.employeemodel.service.CompanyService;
import com.example.employeemodel.service.impl.CompanyServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDto> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        CompanyResponseDto createdCompany = companyService.create(companyDto);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CompanyResponseDto>> getAllCompanies() {
        List<CompanyResponseDto> companies = companyService.getAll();
        return new ResponseEntity<>(companies,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> getCompanyById(@PathVariable Long id) {
        CompanyResponseDto company = companyService.getById(id);
        return new ResponseEntity<>(company,HttpStatus.OK);    }


    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable(name = "id") Long id,
                                                    @Parameter(required = false,
                                                            schema = @Schema(implementation = CompanyDto.class))
                                                    @RequestBody String companyDto
                                                    ) {

        return ResponseEntity.status(HttpStatus.OK).body(companyService.update(id,companyDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
