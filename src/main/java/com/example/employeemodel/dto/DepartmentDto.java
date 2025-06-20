package com.example.employeemodel.dto;

import com.example.employeemodel.helper.validations.NullOrNotBlank;
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
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class DepartmentDto {

    @NullOrNotBlank(min = 2, max = 100, isMandatory = "yes", message = "Department name is required and must be between {min} and {max} characters.")
    private String name;

    @NullOrNotBlank(isMandatory = "no", min = 5, max = 255, message = "Description (optional) must be between {min} and {max} characters.")
    private String description;

    @NotNull(message = "Company ID is required for department.")
    private Long companyId;
}
