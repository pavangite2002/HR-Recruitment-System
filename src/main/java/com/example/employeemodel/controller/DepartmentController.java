package com.example.employeemodel.controller;

import com.example.employeemodel.dto.DepartmentDto;
import com.example.employeemodel.dto.DepartmentResponseDto;
import com.example.employeemodel.dto.RestApiResponse;
import com.example.employeemodel.service.DepartmentService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.employeemodel.helper.utils.ResponseBuilder.success;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<RestApiResponse<DepartmentResponseDto>> createDepartment(
            @Valid @RequestBody DepartmentDto dto) {
        DepartmentResponseDto created = departmentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(success("Department created successfully", created));
    }

    @GetMapping
    public ResponseEntity<RestApiResponse<List<DepartmentResponseDto>>> getAllDepartments() {
        List<DepartmentResponseDto> departments = departmentService.getAll();
        return ResponseEntity.ok(success("Departments fetched successfully", departments));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<DepartmentResponseDto>> getDepartmentById(@PathVariable Long id) {
        DepartmentResponseDto department = departmentService.getById(id);
        return ResponseEntity.ok(success("Department fetched successfully ", department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<DepartmentResponseDto>> updateDepartment(
            @PathVariable Long id,
            @Parameter(required = false, schema = @Schema(implementation = DepartmentDto.class))
            @RequestBody String departmentDtoJson) {
        DepartmentResponseDto updated = departmentService.update(id, departmentDtoJson);
        return ResponseEntity.ok(success("Department updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<String>> deleteDepartment(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.ok(success("Department deleted successfully"));
    }
}
