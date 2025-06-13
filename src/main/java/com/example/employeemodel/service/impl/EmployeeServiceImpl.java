package com.example.employeemodel.service.impl;

import com.example.employeemodel.controller.MapperUtil;
import com.example.employeemodel.dto.EmployeeDto;
import com.example.employeemodel.model.Employee;
import com.example.employeemodel.repository.EmployeeRepository;
import com.example.employeemodel.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
//    private final MapperUtil mapperUtil;



//    public Employee addEmployee(EmployeeDto employeeDto) {
//        return employeeRepository.save(mapperUtil.mapObject(employeeDto, Employee.class));
//
//    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();

    }

    public Optional<Employee> getEmployeeById(Long id) {
        return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Invalid Employee Id")));
    }

    public void deleteEmployeeById(Long id) {
        if(!employeeRepository.existsById(id)){
            throw new RuntimeException("Invalid Employee Id");
        }else employeeRepository.deleteById(id);
    }

}
