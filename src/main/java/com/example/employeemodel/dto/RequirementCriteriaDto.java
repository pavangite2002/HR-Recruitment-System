package com.example.employeemodel.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequirementCriteriaDto {

    private Long id;

    @NotNull(message = "Position ID is required for requirement criteria.")
    private Long positionId;

    @Min(value = 0, message = "Minimum experience must be 0 or more.")
    @Max(value = 50, message = "Minimum experience cannot exceed 50 years.")
    private int minExperience;

    @NotEmpty(message = "At least one required skill must be specified.")
    private List<String> requiredSkills;
}
