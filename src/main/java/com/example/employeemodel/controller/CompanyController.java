package com.example.employeemodel.controller;

import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.dto.CompanyResponseDto;
import com.example.employeemodel.dto.RestApiResponse;
import com.example.employeemodel.service.CompanyService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.example.employeemodel.util.ResponseBuilder.*;
import java.util.List;


@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<RestApiResponse<CompanyResponseDto>> createCompany(@Valid @RequestBody CompanyDto companyDto) {
        CompanyResponseDto createdCompany = companyService.create(companyDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(success("Company created successfully", createdCompany));
    }

    @GetMapping
    public ResponseEntity<RestApiResponse<List<CompanyResponseDto>>> getAllCompanies() {
        List<CompanyResponseDto> companies = companyService.getAll();
        return ResponseEntity.ok(success("Companies fetched successfully", companies));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<CompanyResponseDto>> getCompanyById(@PathVariable Long id) {
            CompanyResponseDto company = companyService.getById(id);
            return ResponseEntity.ok(success("Company found", company));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<CompanyResponseDto>> updateCompany(
            @PathVariable(name = "id") Long id,
            @Parameter(required = false, schema = @Schema(implementation = CompanyDto.class))
            @RequestBody String companyDto) throws BadRequestException {
            CompanyResponseDto updated = companyService.update(id, companyDto);
            return ResponseEntity.ok(success("Company updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<String>> deleteCompany(@PathVariable Long id) {
            companyService.delete(id);
            return ResponseEntity.ok(success("Company deleted successfully"));
    }
}
