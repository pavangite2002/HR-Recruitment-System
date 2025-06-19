package com.example.employeemodel.dto;

import com.example.employeemodel.validation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.Valid;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyDto {

    @NullOrNotBlank(min = 2, max = 100, isMandatory = "yes", message = "Company name is required and must be between {min} and {max} characters.")
    private String name;

    @NullOrNotBlank(min = 5, max = 255, isMandatory = "no", message = "Address (optional) must be between {min} and {max} characters if provided.")
    private String address;

    @NullOrNotBlank(min = 2, max = 50, isMandatory = "yes", message = "Industry (optional) must be between {min} and {max} characters if provided.")
    private String industry;

    @NullOrNotBlank(min = 2, max = 100, isMandatory = "yes", message = "Website must be between {min} and {max} characters if provided.")
    private String website;

}

