package com.example.employeemodel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {
    private Long id;
    private String candidateName;
    private String email;
    private String resumeUrl;
    private Long appliedPositionId;
}
