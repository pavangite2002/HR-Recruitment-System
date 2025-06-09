package com.example.employeemodel.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewerDto {

    private Long id;

    @NotNull(message = "Employee ID is required for the interviewer.")
    private Long employeeId;

    @NotEmpty(message = "At least one interview batch ID must be assigned to the interviewer.")
    private List<Long> interviewBatchIds;
}