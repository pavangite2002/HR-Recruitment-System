package com.example.employeemodel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionDto {
    private Long id;
    private String title; // e.g., Java Jr Developer
    private Long departmentId;
    private Long subCategoryId;
    private RequirementCriteriaDto criteria;
}
