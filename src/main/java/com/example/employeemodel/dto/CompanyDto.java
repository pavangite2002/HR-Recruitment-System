package com.example.employeemodel.dto;

import com.example.employeemodel.validation.NullOrNotBlank;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {

    private Long id;

    @NullOrNotBlank(min = 2, max = 100, isMandatory = "yes", message = "Company name is required and must be between {min} and {max} characters.")
    private String name;

    @NullOrNotBlank(min = 5, max = 255, isMandatory = "no", message = "Address (optional) must be between {min} and {max} characters if provided.")
    private String address;

    @NullOrNotBlank(min = 2, max = 100, isMandatory = "no", message = "Industry (optional) must be between {min} and {max} characters if provided.")
    private String industry;

    @Valid
    private List<DeptDto> departments;
}
