package com.example.employeemodel.controller;

import com.example.employeemodel.dto.RestApiResponse;
import com.example.employeemodel.dto.SubCategoryDto;
import com.example.employeemodel.dto.SubCategoryResponseDto;
import com.example.employeemodel.service.SubCategoryService;
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
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
    public ResponseEntity<RestApiResponse<SubCategoryResponseDto>> createSubCategory(
            @Valid @RequestBody SubCategoryDto dto) {
        SubCategoryResponseDto created = subCategoryService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(success("SubCategory created successfully", created));
    }

    @GetMapping
    public ResponseEntity<RestApiResponse<List<SubCategoryResponseDto>>> getAllSubCategories() {
        List<SubCategoryResponseDto> subCategories = subCategoryService.getAll();
        return ResponseEntity.ok(success("SubCategories fetched successfully", subCategories));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<SubCategoryResponseDto>> getSubCategoryById(@PathVariable Long id) {
        SubCategoryResponseDto subCategory = subCategoryService.getById(id);
        return ResponseEntity.ok(success("SubCategory fetched successfully", subCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<SubCategoryResponseDto>> updateSubCategory(
            @PathVariable Long id,
            @Parameter(required = false, schema = @Schema(implementation = SubCategoryDto.class))
            @RequestBody String subCategoryDtoJson) {
        SubCategoryResponseDto updated = subCategoryService.update(id, subCategoryDtoJson);
        return ResponseEntity.ok(success("SubCategory updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<String>> deleteSubCategory(@PathVariable Long id) {
        subCategoryService.delete(id);
        return ResponseEntity.ok(success("SubCategory deleted successfully"));
    }
}
