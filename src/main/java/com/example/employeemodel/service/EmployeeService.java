package com.example.employeemodel.service;

import com.example.employeemodel.dto.CompanyResponseDto;
import com.example.employeemodel.dto.EmployeeDto;
import com.example.employeemodel.dto.EmployeeResponseDto;
import com.example.employeemodel.model.Employee;
import org.apache.coyote.BadRequestException;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    EmployeeResponseDto addEmployee(EmployeeDto employeeDto);
    List<EmployeeResponseDto> getAllEmployee();
    EmployeeResponseDto getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    EmployeeResponseDto update(Long id, String employeeDto) throws BadRequestException;
}
