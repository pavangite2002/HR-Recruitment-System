package com.example.employeemodel.service;

import com.example.employeemodel.dto.CandidateRequestDto;
import com.example.employeemodel.dto.CandidateResponseDto;
import com.example.employeemodel.helper.projections.CandidateProjectionResponse;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface CandidateService {
    CandidateResponseDto create(@Valid CandidateRequestDto candidateDto);

    List<CandidateResponseDto> getAll();

    CandidateResponseDto getById(Long id);

    CandidateResponseDto update(Long id, String candidateDto) throws BadRequestException;

    void delete(Long id);

    List<CandidateProjectionResponse> getAllProjections();

}
