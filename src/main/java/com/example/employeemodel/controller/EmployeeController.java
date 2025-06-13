package com.example.employeemodel.controller;

import com.example.employeemodel.dto.EmployeeDto;
import com.example.employeemodel.model.Employee;
import com.example.employeemodel.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

//    @PostMapping
//    public ResponseEntity<Employee> create(@Valid @RequestBody EmployeeDto dto) {
//        return new ResponseEntity<>(employeeService.addEmployee(dto), HttpStatus.CREATED);
//    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return new ResponseEntity<>(employeeService.getAllEmployee(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Employee>> getEmployeeById(@PathVariable (name = "id") Long id) {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteemployeeById(@PathVariable (name = "id") Long id){
        try {
            employeeService.deleteEmployeeById(id);
            return ResponseEntity.ok("Employee deleted successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
