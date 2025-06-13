package com.example.employeemodel.mapper;


import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.dto.CompanyResponseDto;
import com.example.employeemodel.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = DepartmentMapper.class)
public interface CompanyMapper {


    @Mappings({
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "address", target = "address"),
            @Mapping(source = "industry", target = "industry"),
            @Mapping(source = "website", target = "website"),
            @Mapping(source = "departments", target = "departments")
    })
    Company companyDtoToCompanyEntity(CompanyDto dto);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "industry", target = "industry")
    @Mapping(source = "website", target = "website")
    @Mapping(source = "departments", target = "departments")
    CompanyResponseDto companyToCompanyResponseDto(Company entity);

    List<CompanyResponseDto> listCompanyToCompanyResponseDto(List<Company> companies);

}

