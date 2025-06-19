package com.example.employeemodel.dto;


import com.example.employeemodel.validation.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {

    @NullOrNotBlank(min = 2, max = 30, message = "First name must be between {min} and {max} characters long.")
    private String firstName;

    @NullOrNotBlank(min = 2, max = 30, message = "Last name must be between {min} and {max} characters long.")
    private String lastName;

    @NullOrNotBlank(min = 5, max = 50, isEmail = "yes", message = "Valid email is required and must be between {min} and {max} characters.")
    private String email;

    @NullOrNotBlank(min = 10, max = 10, message = "Phone number must be exactly 10 digits.")
    private String phoneNumber;

    @NullOrNotBlank(min = 2, max = 50, isMandatory = "no", message = "Job title (optional) must be between {min} and {max} characters if provided.")
    private String jobTitle;

    @NullOrNotBlank(min = 1, max = 20, isMandatory = "no", message = "Employee code (optional) must be between {min} and {max} characters if provided.")
    private String employeeCode;

    @NullOrNotBlank(message = "Gender is required.")
    private String gender;

    @Past(message = "Date of birth must be a past date.")
    @NotNull(message = "Date of birth is required.")
    private LocalDate dateOfBirth;

    @NotNull(message = "Date of joining is required.")
    private LocalDate dateOfJoining;

    @NullOrNotBlank(message = "Status is required.")
    private String status;

//    @NotNull(message = "Department ID is required.")
    private Long departmentId;

//    @NotNull(message = "Company ID is required.")
    private Long companyId;

//    @NotNull(message = "Sub-category ID is required.")
    private Long subCategoryId;
}
