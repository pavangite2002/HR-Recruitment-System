package com.example.employeemodel.mapper;


import com.example.employeemodel.dto.DepartmentDto;
import com.example.employeemodel.dto.DepartmentResponseDto;
import com.example.employeemodel.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(source = "companyId", target = "company.id")
    Department deptDtoToDepartment(DepartmentDto departmentDto);

    DepartmentResponseDto departmentToDepartmentDto(Department department);

}
