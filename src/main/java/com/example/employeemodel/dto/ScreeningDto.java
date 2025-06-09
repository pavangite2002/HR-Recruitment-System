package com.example.employeemodel.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScreeningDto {

    private Long id;
    private Long candidateCVId;
    private String screeningResult; // "Selected" or "Rejected"
    private String rejectionReason;
}
