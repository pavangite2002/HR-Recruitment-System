package com.example.employeemodel.service.impl;

import com.example.employeemodel.dto.CompanyDto;
import com.example.employeemodel.dto.CompanyResponseDto;
import com.example.employeemodel.exception.ResourceNotFoundException;
import com.example.employeemodel.mapper.CompanyMapper;
import com.example.employeemodel.model.Company;
import com.example.employeemodel.repository.CompanyRepository;
import com.example.employeemodel.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

import static com.example.employeemodel.util.UpdateUtils.getJsonNode;
import static com.example.employeemodel.util.UpdateUtils.readValue;

@Service
@Transactional
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;

    @Override
    public CompanyResponseDto create(CompanyDto dto) {
        return companyMapper.companyToCompanyResponseDto( companyRepository.save(companyMapper.companyDtoToCompanyEntity(dto)));
    }

    @Override
    public List<CompanyResponseDto> getAll() {
        List<Company> companies = companyRepository.findAll();
        return companyMapper.listCompanyToCompanyResponseDto(companies);
    }


    @Override
    public CompanyResponseDto getById(Long id) {
        return companyMapper.companyToCompanyResponseDto(companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id)));
    }


    public CompanyResponseDto update(Long id, String updatedDto) throws BadRequestException {

        Company companyFieldToUpdate = Optional.ofNullable(readValue(updatedDto, Company.class))
                .orElseThrow(() -> new BadRequestException("Invalid Company request payload"));

        Company existingCompany = companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with Id : " + id));

        JsonNode jsonNode = getJsonNode(updatedDto);
        if (jsonNode.has("name")) {
            int length = companyFieldToUpdate.getName().length();
            if (!(length >= 1 && length <= 50)) {
                throw new BadRequestException("Name is required and must be between 1 and 50 characters");
            }
            existingCompany.setName(companyFieldToUpdate.getName());
        }
        if (jsonNode.has("address")) {
            int length = companyFieldToUpdate.getAddress().length();
            if (!(length >= 1 && length <= 50)) {
                throw new BadRequestException("Address is required and  must be between 1 and 50 characters");
            }
            existingCompany.setAddress(companyFieldToUpdate.getAddress());
        }
        if (jsonNode.has("website")) {
            int length = companyFieldToUpdate.getWebsite().length();
            if (!(length >= 2 && length <= 100)) {
                throw new BadRequestException("Website is required and  must be between 2 and 100 characters");
            }
            existingCompany.setWebsite(companyFieldToUpdate.getWebsite());
        }
        if (jsonNode.has("industry")) {
            int length = companyFieldToUpdate.getIndustry().length();
            if (!(length >= 2 && length <= 100)) {
                throw new BadRequestException("industry is required and  must 50 characters");
            }
            existingCompany.setIndustry(companyFieldToUpdate.getIndustry());
        }
        return companyMapper.companyToCompanyResponseDto(companyRepository.save(existingCompany));
    }

    public void delete(Long id) {
        companyRepository.delete(companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + id)));
    }
}
