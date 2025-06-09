package com.example.employeemodel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewBatchDto {
    private Long id;
    private LocalDate interviewDate;
    private String timeSlot;
    private Long positionId;
    private List<Long> candidateIds;
}
