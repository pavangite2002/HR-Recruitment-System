package com.example.employeemodel.service.impl;

import com.example.employeemodel.dto.CandidateRequestDto;
import com.example.employeemodel.helper.projections.CandidateProjectionResponse;
import com.example.employeemodel.model.Position;
import com.example.employeemodel.service.CandidateService;
import com.example.employeemodel.dto.CandidateResponseDto;
import com.example.employeemodel.exception.ResourceNotFoundException;
import com.example.employeemodel.mapper.CandidateMapper;
import com.example.employeemodel.model.Candidate;
import com.example.employeemodel.repository.CandidateRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.employeemodel.helper.utils.UpdateUtils.getJsonNode;
import static com.example.employeemodel.helper.utils.UpdateUtils.readValue;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;

    @Override
    public CandidateResponseDto create(CandidateRequestDto dto) {
        return candidateMapper.candidateEntityToCandidateResponseDto(candidateRepository.save(candidateMapper.candidateRequestDtoToCandidateEntity(dto)));
    }

    @Override
    public List<CandidateResponseDto> getAll() {
        return candidateRepository.findAll().stream().map(candidateMapper::candidateEntityToCandidateResponseDto).toList();
    }

    @Override
    public Page<CandidateProjectionResponse> getAllProjections(Pageable pageable) {
        return candidateRepository.findAllProjectedBy(pageable);
    }

    @Override
    public CandidateResponseDto getById(Long id) {
        return candidateMapper.candidateEntityToCandidateResponseDto(candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + id)));
    }

    @Override
    public CandidateResponseDto update(Long id, String updatedDto) throws BadRequestException {

        CandidateRequestDto fieldsToUpdate = Optional.ofNullable(readValue(updatedDto, CandidateRequestDto.class))
                .orElseThrow(() -> new BadRequestException("Invalid Candidate payload"));

        Candidate existing = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + id));

        JsonNode jsonNode = getJsonNode(updatedDto);

        if (jsonNode.has("name")) {
            String fullName = fieldsToUpdate.getName();
            if (fullName == null || fullName.length() < 2 || fullName.length() > 100) {
                throw new BadRequestException("Full name must be between 2 and 100 characters");
            }
            existing.setName(fullName);
        }

        if (jsonNode.has("email")) {
            String email = fieldsToUpdate.getEmail();
            if (email == null || email.length() < 5 || email.length() > 50 || !email.contains("@")) {
                throw new BadRequestException("Invalid email format");
            }
            existing.setEmail(email);
        }

        if (jsonNode.has("dob")) {
            LocalDate dob = fieldsToUpdate.getDob();
            if (dob == null) {
                throw new BadRequestException("Date of birth must be a past date and cannot be null");
            }
            existing.setDob(dob);
        }

        if (jsonNode.has("positionIds")) {
            List<Long> positionIds = fieldsToUpdate.getPositionIds();
            if (positionIds != null && !positionIds.isEmpty()) {
                List<Position> positions = new ArrayList<>(positionIds.stream()
                        .map(idVal -> Position.builder().id(idVal).build())
                        .toList());
                existing.setPositions(positions);
            } else {
                existing.setPositions(null);
            }
        }

        return candidateMapper.candidateEntityToCandidateResponseDto(candidateRepository.save(existing));
    }

    @Override
    public void delete(Long id) {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Candidate not found with ID: " + id));
        candidateRepository.delete(candidate);
    }
}


//        Candidate candidate = candidateMapper.candidateRequestDtoToCandidateEntity(dto);
//        List<Position> pid = new ArrayList<>();
//        for (Long positionIds : dto.getPositionIds() ) {
//            Position position = positionRepository.findById(positionIds)
//                    .orElseThrow(() -> new ResourceNotFoundException(""));
//            pid.add(position);
//        }
//        candidate.setPositions(pid);
//        return candidateMapper.candidateEntityToCandidateResponseDto(candidateRepository.save(candidate));
