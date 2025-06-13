package com.example.employeemodel.service;

import com.example.employeemodel.dto.EmployeeDto;
import com.example.employeemodel.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
//    Employee addEmployee(EmployeeDto employeeDto);
    List<Employee> getAllEmployee();
    Optional<Employee> getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
}
