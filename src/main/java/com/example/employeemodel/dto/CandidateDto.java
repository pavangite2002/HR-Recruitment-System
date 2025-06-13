package com.example.employeemodel.dto;


import com.example.employeemodel.validation.NullOrNotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateDto {
    private Long id;
    @NullOrNotBlank
    private String candidateName;
    @NullOrNotBlank
    private String email;
    private Long appliedPositionId;
}
