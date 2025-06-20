package com.example.employeemodel.dto;

import com.example.employeemodel.helper.validations.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionRequestDto {

    @NullOrNotBlank(min = 2, max = 100, isMandatory = "yes", message = "Position title is required and must be between {min} and {max} characters.")
    private String positionName;

}

//
//@NotNull(message = "Candidate ID is required for the position.")
//private Long candidateId;