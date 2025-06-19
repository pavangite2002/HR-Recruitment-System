package com.example.employeemodel.service.impl;

import com.example.employeemodel.dto.EmployeeDto;
import com.example.employeemodel.dto.EmployeeResponseDto;
import com.example.employeemodel.exception.ResourceNotFoundException;
import com.example.employeemodel.mapper.EmployeeMapper;
import com.example.employeemodel.model.Company;
import com.example.employeemodel.model.Department;
import com.example.employeemodel.model.Employee;
import com.example.employeemodel.model.SubCategory;
import com.example.employeemodel.repository.CompanyRepository;
import com.example.employeemodel.repository.DepartmentRepository;
import com.example.employeemodel.repository.EmployeeRepository;
import com.example.employeemodel.repository.SubCategoryRepository;
import com.example.employeemodel.service.EmployeeService;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.example.employeemodel.util.UpdateUtils.getJsonNode;
import static com.example.employeemodel.util.UpdateUtils.readValue;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final CompanyRepository companyRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeResponseDto addEmployee(EmployeeDto employeeDto) {
        return employeeMapper.employeeToEmployeeResponseDto(employeeRepository.save(employeeMapper.employeeDtoToEmployee(employeeDto)));
    }

    public List<EmployeeResponseDto> getAllEmployee() {
        return employeeMapper.employeeListToEmployeeResponseDtoList(employeeRepository.findAll());
    } // Chances of Exception are less as it may return [] empty list

    public EmployeeResponseDto getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return employeeMapper.employeeToEmployeeResponseDto(employee);
    }


    public void deleteEmployeeById(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee Not Fount with id : " + id);
        } else employeeRepository.deleteById(id);
    }

    public EmployeeResponseDto update(Long id, String updatedDto) throws BadRequestException {
        EmployeeDto employeeFieldsToUpdate = Optional.ofNullable(readValue(updatedDto, EmployeeDto.class))
                .orElseThrow(() -> new BadRequestException("Invalid Employee request payload"));

        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with Id : " + id));

        JsonNode jsonNode = getJsonNode(updatedDto);

        if (jsonNode.has("firstName")) {
            String firstName = employeeFieldsToUpdate.getFirstName();
            if (firstName == null || firstName.length() < 2 || firstName.length() > 30) {
                throw new BadRequestException("First name must be between 2 and 30 characters.");
            }
            existingEmployee.setFirstName(firstName);
        }
        if (jsonNode.has("lastName")) {
            String lastName = employeeFieldsToUpdate.getLastName();
            if (lastName == null || lastName.length() < 2 || lastName.length() > 30) {
                throw new BadRequestException("Last name must be between 2 and 30 characters.");
            }
            existingEmployee.setLastName(lastName);
        }
        if (jsonNode.has("email")) {
            String email = employeeFieldsToUpdate.getEmail();
            if (email == null || email.length() < 5 || email.length() > 50 || !email.contains("@")) {
                throw new BadRequestException("Valid email is required and must be between 5 and 50 characters.");
            }
            existingEmployee.setEmail(email);
        }
        if (jsonNode.has("phoneNumber")) {
            String phone = employeeFieldsToUpdate.getPhoneNumber();
            if (phone == null || phone.length() != 10) {
                throw new BadRequestException("Phone number must be exactly 10 digits.");
            }
            existingEmployee.setPhoneNumber(phone);
        }
        if (jsonNode.has("jobTitle")) {
            String jobTitle = employeeFieldsToUpdate.getJobTitle();
            if (jobTitle != null && (jobTitle.length() < 2 || jobTitle.length() > 50)) {
                throw new BadRequestException("Job title must be between 2 and 50 characters if provided.");
            }
            existingEmployee.setJobTitle(jobTitle);
        }
        if (jsonNode.has("employeeCode")) {
            String code = employeeFieldsToUpdate.getEmployeeCode();
            if (code != null && (code.length() < 1 || code.length() > 20)) {
                throw new BadRequestException("Employee code must be between 1 and 20 characters if provided.");
            }
            existingEmployee.setEmployeeCode(code);
        }
        if (jsonNode.has("gender")) {
            String gender = employeeFieldsToUpdate.getGender();
            if (gender == null || gender.isBlank()) {
                throw new BadRequestException("Gender is required.");
            }
            existingEmployee.setGender(gender);
        }

        if (jsonNode.has("dateOfBirth")) {
            if (employeeFieldsToUpdate.getDateOfBirth() == null ||
                    !employeeFieldsToUpdate.getDateOfBirth().isBefore(LocalDate.now())) {
                throw new BadRequestException("Date of birth must be a past date.");
            }
            existingEmployee.setDateOfBirth(employeeFieldsToUpdate.getDateOfBirth());
        }
        if (jsonNode.has("dateOfJoining")) {
            if (employeeFieldsToUpdate.getDateOfJoining() == null) {
                throw new BadRequestException("Date of joining is required.");
            }
            existingEmployee.setDateOfJoining(employeeFieldsToUpdate.getDateOfJoining());
        }
        if (jsonNode.has("status")) {
            String status = employeeFieldsToUpdate.getStatus();
            if (status == null || status.isBlank()) {
                throw new BadRequestException("Status is required.");
            }
            existingEmployee.setStatus(status);
        }
        if (jsonNode.has("departmentId")) {
            Long deptId = employeeFieldsToUpdate.getDepartmentId();
            Department dept = departmentRepository.findById(deptId)
                    .orElseThrow(() -> new ResourceNotFoundException("Department not found with ID: " + deptId));
            existingEmployee.setDepartment(dept);
        }
        if (jsonNode.has("companyId")) {
            Long companyId = employeeFieldsToUpdate.getCompanyId();
            Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new ResourceNotFoundException("Company not found with ID: " + companyId));
            existingEmployee.setCompany(company);
        }
        if (jsonNode.has("subCategoryId")) {
            Long subCatId = employeeFieldsToUpdate.getSubCategoryId();
            SubCategory subCategory = subCategoryRepository.findById(subCatId)
                    .orElseThrow(() -> new ResourceNotFoundException("SubCategory not found with ID: " + subCatId));
            existingEmployee.setSubCategory(subCategory);
        }
        return employeeMapper.employeeToEmployeeResponseDto(employeeRepository.save(existingEmployee));
    }
}