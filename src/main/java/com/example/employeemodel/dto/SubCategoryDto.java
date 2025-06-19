package com.example.employeemodel.dto;

import com.example.employeemodel.validation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SubCategoryDto {

    @NullOrNotBlank(min = 2, max = 100, isMandatory = "yes", message = "Sub-category name is required and must be between {min} and {max} characters.")
    private String name;

}

//    @NotNull(message = "Department ID is required for sub-category.")
//    private Long departmentId;