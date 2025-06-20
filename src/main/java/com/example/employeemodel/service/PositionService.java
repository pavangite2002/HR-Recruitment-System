package com.example.employeemodel.service;

import com.example.employeemodel.dto.PositionRequestDto;
import com.example.employeemodel.dto.PositionResponseDto;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;

import java.util.List;

public interface PositionService {

    PositionResponseDto create(@Valid PositionRequestDto positionDto);

    List<PositionResponseDto> getAll();

    PositionResponseDto getById(Long id);

    PositionResponseDto update(Long id, String PositionRequestDto) throws BadRequestException;

    void delete(Long id);
}
