package com.example.employeemodel.dto;


import com.example.employeemodel.validation.NullOrNotBlank;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
//    private String resumeUrl;
    private Long appliedPositionId;
}
