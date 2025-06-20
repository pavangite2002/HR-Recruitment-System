package com.example.employeemodel.helper.projections;

import com.example.employeemodel.model.Position;

import java.time.LocalDate;
import java.util.List;

public interface CandidateProjectionResponse {
    Long getId();
    String getName();
    String getEmail();
    LocalDate getDob();
    List<Position> getPositions();
}

//default LocalDate getDob() {
//    return null;
//}
//default List<PositionProjectionResponse> getPositionProjectionResponse() {
//    return null;
//}