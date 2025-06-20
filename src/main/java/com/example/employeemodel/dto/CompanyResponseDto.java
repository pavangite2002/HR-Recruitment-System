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
@Builder
public class CompanyResponseDto {

    private Long id;
    private String name;
    private String address;
    private String industry;
    private String website;
    private List<DepartmentResponseDto> departments;
}
