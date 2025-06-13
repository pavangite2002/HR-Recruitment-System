package com.example.employeemodel.mapper;


import com.example.employeemodel.dto.DepartmentDto;
import com.example.employeemodel.model.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = SubCategoryMapper.class)
public interface DepartmentMapper {

    @Mapping(source = "subCategories", target = "subCategories")
    Department deptDtoToDepartment(DepartmentDto departmentDto);
}

