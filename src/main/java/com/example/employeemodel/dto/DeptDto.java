package com.example.employeemodel.dto;

import com.example.employeemodel.validation.NullOrNotBlank;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeptDto {

    private Long id;

    @NullOrNotBlank(min = 2, max = 100, isMandatory = "yes", message = "Department name is required and must be between {min} and {max} characters.")
    private String name;

    @NotNull(message = "Company ID is required for department.")
    private Long companyId;

    @Valid
    private List<SubCategoryDto> subCategories;
}
