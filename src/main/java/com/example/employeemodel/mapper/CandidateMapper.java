package com.example.employeemodel.mapper;


import com.example.employeemodel.dto.CandidateRequestDto;
import com.example.employeemodel.dto.CandidateResponseDto;
import com.example.employeemodel.model.Candidate;
import com.example.employeemodel.model.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapper {

    CandidateResponseDto candidateEntityToCandidateResponseDto(Candidate entity);

    @Mapping(source = "positionIds", target = "positions")
    Candidate candidateRequestDtoToCandidateEntity(CandidateRequestDto dto);

    default List<Position> mapPositionIdsToPositions(List<Long> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> Position.builder().id(id).build())
                .toList();
    }
}
