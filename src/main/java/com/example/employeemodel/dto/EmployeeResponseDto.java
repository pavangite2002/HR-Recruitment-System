package com.example.employeemodel.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String jobTitle;
    private String employeeCode;
    private String gender;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private String status;

    private Long companyId;
    private String companyName;

    private Long departmentId;
    private String departmentName;

    private Long subCategoryId;
    private String subCategoryName;
}

