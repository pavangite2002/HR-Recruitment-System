package com.example.employeemodel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    private String name;
    private String role;
    private Long companyId;
    private Long departmentId;
    private Long subCategoryId;
}
