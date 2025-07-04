package com.example.employeemodel.repository;

import com.example.employeemodel.helper.projections.CandidateProjectionResponse;
import com.example.employeemodel.model.Candidate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    @Query("SELECT c FROM Candidate c LEFT JOIN FETCH c.positions") // for projections
    Page<CandidateProjectionResponse> findAllProjectedBy(Pageable pageable);
}

// If you're using Spring Data default method naming, even this would work:

//List<CandidateProjectionResponse> findAllBy();