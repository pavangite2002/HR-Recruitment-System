package com.example.employeemodel.mapper;


import com.example.employeemodel.dto.PositionRequestDto;
import com.example.employeemodel.dto.PositionResponseDto;
import com.example.employeemodel.model.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PositionMapper {

    Position positionRequestDtoToPositionEntity(PositionRequestDto dto);

    PositionResponseDto positionEntityToPositionResponseDto(Position save);
}
