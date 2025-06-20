package com.example.employeemodel.mapper;

import com.example.employeemodel.dto.SubCategoryDto;
import com.example.employeemodel.dto.SubCategoryResponseDto;
import com.example.employeemodel.model.SubCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubCategoryMapper {

    @Mapping(source = "departmentId", target = "department.id")
    SubCategory subCategoryDtoToSubCategory(SubCategoryDto subCategoryDto);

    @Mapping(source = "employee", target = "employeeResponseDtos")
    SubCategoryResponseDto subCategoryToSubCategoryResponseDto(SubCategory subCategory);
}
