package com.example.employeemodel.controller;

import com.example.employeemodel.dto.CandidateRequestDto;
import com.example.employeemodel.dto.CandidateResponseDto;
import com.example.employeemodel.dto.RestApiResponse;
import com.example.employeemodel.helper.projections.CandidateProjectionResponse;
import com.example.employeemodel.service.CandidateService;
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
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<RestApiResponse<CandidateResponseDto>> createCandidate(@Valid @RequestBody CandidateRequestDto candidateDto) {
        CandidateResponseDto created = candidateService.create(candidateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(success("Candidate created successfully", created));
    }

    @GetMapping
    public ResponseEntity<RestApiResponse<List<CandidateResponseDto>>> getAllCandidates() {
        return ResponseEntity.ok(success("All candidates fetched successfully", candidateService.getAll()));
    }

    @GetMapping("/projected")
    public ResponseEntity<RestApiResponse<List<CandidateProjectionResponse>>> getAllProjectedCandidates() {
        return ResponseEntity.ok(success("All projected candidates fetched", candidateService.getAllProjections()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<CandidateResponseDto>> getCandidateById(@PathVariable Long id) {
        CandidateResponseDto dto = candidateService.getById(id);
        return ResponseEntity.ok(success("Candidate found", dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RestApiResponse<CandidateResponseDto>> updateCandidate(
            @PathVariable Long id,
            @Parameter(schema = @Schema(implementation = CandidateRequestDto.class))
            @RequestBody String candidateDto) throws BadRequestException {
        CandidateResponseDto updated = candidateService.update(id, candidateDto);
        return ResponseEntity.ok(success("Candidate updated successfully", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RestApiResponse<String>> deleteCandidate(@PathVariable Long id) {
        candidateService.delete(id);
        return ResponseEntity.ok(success("Candidate deleted successfully"));
    }
}
