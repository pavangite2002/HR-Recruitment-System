package com.example.employeemodel.service.impl;

import com.example.employeemodel.dto.PositionRequestDto;
import com.example.employeemodel.dto.PositionResponseDto;
import com.example.employeemodel.exception.ResourceNotFoundException;
import com.example.employeemodel.mapper.PositionMapper;
import com.example.employeemodel.model.Position;
import com.example.employeemodel.repository.CandidateRepository;
import com.example.employeemodel.repository.PositionRepository;
import com.example.employeemodel.service.PositionService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.employeemodel.helper.utils.UpdateUtils.getJsonNode;
import static com.example.employeemodel.helper.utils.UpdateUtils.readValue;

@Service
@Transactional
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;
    private final CandidateRepository candidateRepository;
    private final PositionMapper positionMapper;

    @Override
    public PositionResponseDto create(PositionRequestDto dto) {
        return positionMapper.positionEntityToPositionResponseDto(positionRepository.save(positionMapper.positionRequestDtoToPositionEntity(dto)));
    }

    @Override
    public List<PositionResponseDto> getAll() {
        return positionRepository.findAll().stream().map(positionMapper::positionEntityToPositionResponseDto).toList();
    }

    @Override
    public PositionResponseDto getById(Long id) {
        return positionMapper.positionEntityToPositionResponseDto(positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with ID: " + id)));
    }

    @Override
    public PositionResponseDto update(Long id, String updatedDto) throws BadRequestException {

        Position fieldsToUpdate = Optional.ofNullable(readValue(updatedDto, Position.class))
                .orElseThrow(() -> new BadRequestException("Invalid Position request payload"));

        Position existing = positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with ID: " + id));

        JsonNode jsonNode = getJsonNode(updatedDto);

        if (jsonNode.has("title")) {
            int length = fieldsToUpdate.getPositionName().length();
            if (!(length >= 2 && length <= 100)) {
                throw new BadRequestException("Title must be between 2 and 100 characters");
            }
            existing.setPositionName(fieldsToUpdate.getPositionName());
        }
        return positionMapper.positionEntityToPositionResponseDto(positionRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        positionRepository.delete(positionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Position not found with ID: " + id)));
    }
}

//        Candidate candidate = candidateRepository.findById(dto.getCandidateId())
//                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + dto.getCandidateId()));