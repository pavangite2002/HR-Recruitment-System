package com.example.employeemodel.controller;

import com.example.employeemodel.dto.PositionRequestDto;
import com.example.employeemodel.dto.PositionResponseDto;
import com.example.employeemodel.dto.RestApiResponse;
import com.example.employeemodel.service.PositionService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.employeemodel.helper.utils.ResponseBuilder.*;

@RestController
@RequestMapping("/api/positions")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping
    public ResponseEntity<RestApiResponse<PositionResponseDto>> createPosition(@Valid @RequestBody PositionRequestDto positionDto) {
        PositionResponseDto createdPosition = positionService.create(positionDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(success("Position created successfully", createdPosition));
    }

    @GetMapping
    public ResponseEntity<RestApiResponse<List<PositionResponseDto>>> getAllPositions() {
        List<PositionResponseDto> positions = positionService.getAll();
        return ResponseEntity.ok(success("Positions fetched successfully", positions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<PositionResponseDto>> getPositionById(@PathVariable Long id) {
        PositionResponseDto position = positionService.getById(id);
        return ResponseEntity.ok(success("Position found", position));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<PositionResponseDto>> updatePosition(
            @PathVariable(name = "id") Long id,
            @Parameter(required = false, schema = @Schema(implementation = PositionRequestDto.class))
            @RequestBody String positionDto) throws BadRequestException {
        PositionResponseDto updated = positionService.update(id, positionDto);
        return ResponseEntity.ok(success("Position updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<String>> deletePosition(@PathVariable Long id) {
        positionService.delete(id);
        return ResponseEntity.ok(success("Position deleted successfully"));
    }
}

