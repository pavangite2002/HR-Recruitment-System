package com.example.employeemodel.mapper;

import com.example.employeemodel.dto.EmployeeDto;
import com.example.employeemodel.model.Company;
import com.example.employeemodel.model.Department;
import com.example.employeemodel.model.SubCategory;
import org.mapstruct.Mapper;
import com.example.employeemodel.dto.EmployeeResponseDto;
import com.example.employeemodel.model.Employee;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "company.name", target = "companyName")
    @Mapping(source = "department.id", target = "departmentId")
    @Mapping(source = "department.name", target = "departmentName")
    @Mapping(source = "subCategory.id", target = "subCategoryId")
    @Mapping(source = "subCategory.name", target = "subCategoryName")
    EmployeeResponseDto employeeToEmployeeResponseDto(Employee employee);

    List<EmployeeResponseDto> employeeListToEmployeeResponseDtoList(List<Employee> employees);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", source = "companyId", qualifiedByName = "mapCompany")
    @Mapping(target = "department", source = "departmentId", qualifiedByName = "mapDepartment")
    @Mapping(target = "subCategory", source = "subCategoryId", qualifiedByName = "mapSubCategory")
    Employee employeeDtoToEmployee(EmployeeDto dto);

    @Named("mapCompany")
    default Company mapCompany(Long id) {
        if (id == null) return null;
        Company c = new Company();
        c.setId(id);
        return c;
    }

    @Named("mapDepartment")
    default Department mapDepartment(Long id) {
        if (id == null) return null;
        Department d = new Department();
        d.setId(id);
        return d;
    }

    @Named("mapSubCategory")
    default SubCategory mapSubCategory(Long id) {
        if (id == null) return null;
        SubCategory s = new SubCategory();
        s.setId(id);
        return s;
    }
}
