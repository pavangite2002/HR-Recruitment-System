package com.example.employeemodel.dto;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CompanyDto {
    private Long id;
    private String name;
    private String address;
    private String industry;
    private List<DeptDto> departments;
}
