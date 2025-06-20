package com.example.employeemodel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubCategoryResponseDto {
    private Long id;
    private String name;
    private List<EmployeeResponseDto> employeeResponseDtos;
}
