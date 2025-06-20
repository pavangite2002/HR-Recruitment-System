package com.example.employeemodel.dto;

import com.example.employeemodel.helper.validations.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
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

