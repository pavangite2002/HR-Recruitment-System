package com.example.employeemodel.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateResponseDto {
    private Long id;
    private String name;
    private String email;
    private String dob;
    private List<PositionResponseDto> positions;
}
