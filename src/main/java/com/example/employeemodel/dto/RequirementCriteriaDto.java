package com.example.employeemodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequirementCriteriaDto {

    private Long id;
    private Long positionId; // One-to-one with PositionDTO
    private int minExperience;
    private List<String> requiredSkills;
}
