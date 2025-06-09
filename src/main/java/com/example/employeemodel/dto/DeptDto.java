package com.example.employeemodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptDto {
    private Long id;
    private String name;
    private Long companyId; // Foreign key reference
    private List<SubCategoryDto> subCategories;
}
