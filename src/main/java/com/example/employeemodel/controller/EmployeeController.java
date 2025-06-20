package com.example.employeemodel.controller;

import com.example.employeemodel.dto.*;
import com.example.employeemodel.service.EmployeeService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.employeemodel.helper.utils.ResponseBuilder.*;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<RestApiResponse<EmployeeResponseDto>> create(@Valid @RequestBody EmployeeDto dto) {
        EmployeeResponseDto saved = employeeService.addEmployee(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(success("Employee created successfully", saved));
    }

    @GetMapping
    public ResponseEntity<RestApiResponse<List<EmployeeResponseDto>>> getAll() {
        List<EmployeeResponseDto> list = employeeService.getAllEmployee();
        return ResponseEntity.ok(success("All employees fetched successfully", list));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<EmployeeResponseDto>> getEmployeeById(@PathVariable Long id) {
        EmployeeResponseDto dto = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(success("Employee found", dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<String>> deleteEmployeeById(@PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(success("Employee deleted successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<EmployeeResponseDto>> updateEmployee(
            @PathVariable(name = "id") Long id,
            @Parameter(required = false, schema = @Schema(implementation = EmployeeDto.class))
            @RequestBody String companyDto) throws BadRequestException {
        EmployeeResponseDto updated = employeeService.update(id, companyDto);
        return ResponseEntity.ok(success("Company updated successfully", updated));
    }

}
