package com.example.employeemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentResponseDto {

    private Long id;
    private String name;
    private String description;
    private List<SubCategoryDto> subCategories;
    private List<EmployeeDto> employees;
}
