package com.example.employeemodel.dto;

import com.example.employeemodel.helper.validations.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateRequestDto {

    @NullOrNotBlank(min = 1, max = 50, isMandatory = "yes", message = "Name is required and must be between {min} and {max} characters.")
    private String name;

    @NullOrNotBlank(isMandatory = "yes", min = 5, max = 100, message = "Email is required and must be valid.")
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @Valid
    private List<Long> positionIds;
}
