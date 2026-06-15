package com.monocept.DepartmentEmployeeManagement.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.monocept.DepartmentEmployeeManagement.dto.DepartmentRequestDto;
import com.monocept.DepartmentEmployeeManagement.dto.DepartmentResponseDto;
import com.monocept.DepartmentEmployeeManagement.dto.EmployeeRequestDto;
import com.monocept.DepartmentEmployeeManagement.dto.PageResponseDto;
import com.monocept.DepartmentEmployeeManagement.exception.DuplicateResourceException;
import com.monocept.DepartmentEmployeeManagement.exception.ResourceNotFoundException;
import com.monocept.DepartmentEmployeeManagement.model.Department;
import com.monocept.DepartmentEmployeeManagement.model.Employee;
import com.monocept.DepartmentEmployeeManagement.repository.DepartmentRepository;
import com.monocept.DepartmentEmployeeManagement.repository.EmployeeRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {
	private DepartmentRepository departmentRepository;
	private EmployeeRepository employeeRepository;
//	previous project me manually convert kr rhe the dto <-> Entity,bt now we are using ModelMapper
	private ModelMapper modelMapper;

//1.Create
	@Override
	public DepartmentResponseDto createDepartment(DepartmentRequestDto departmentRequestDto) {
		log.info("Executing service operation: Create Department");
		// Check duplicate department name
		if (departmentRepository.existsByDepartmentName(departmentRequestDto.getDepartmentName())) {
			 log.warn("Department name already exists: {}", departmentRequestDto.getDepartmentName());
			 throw new DuplicateResourceException("Department name already exists: " + departmentRequestDto.getDepartmentName());
		}
		// DTO -> Entity
		Department department = modelMapper.map(departmentRequestDto, Department.class);

		// Set Department in each Employee
		List<Employee> Employees = department.getEmployees();
		for (Employee emp : Employees) {
			emp.setDepartment(department);

		}
		// Save Department and Employees
		Department savedDepartment = departmentRepository.save(department);
		log.info("Department created successfully with ID: {}", savedDepartment.getId());
		// Entity -> Response DTO
		return modelMapper.map(savedDepartment, DepartmentResponseDto.class);
	}

//2.read all
	@Override
	public List<DepartmentResponseDto> getAllDepartments() {
		log.info("Executing service operation: Read All Departments");
		List<Department> departments = departmentRepository.findAll();
		List<DepartmentResponseDto> responseDtos = new ArrayList<>();
		for (Department department : departments) {
			DepartmentResponseDto dto = modelMapper.map(department, DepartmentResponseDto.class);
			responseDtos.add(dto);
		}

		return responseDtos;
//		return departments.stream()
//	            .map(department -> modelMapper.map(
//	                    department,
//	                    DepartmentResponseDto.class))
//	            .toList();
	}

	@Override
	public PageResponseDto<DepartmentResponseDto> getAllDepartmentsWithPagination(int pageNo, int pageSize) {
		log.info("Executing service operation: Read Paginated Departments");
		// Validation
		if (pageNo < 0) {
			throw new IllegalArgumentException("Page number must not be negative");
		}

		if (pageSize <= 0) {
			throw new IllegalArgumentException("Page size must be greater than zero");
		}

		if (pageSize > 100) {
			throw new IllegalArgumentException("Page size must not exceed 100");
		}

		Pageable pageable = PageRequest.of(pageNo, pageSize);

		Page<Department> departmentPage = departmentRepository.findAll(pageable);

		// Convert Entity List -> DTO List
//	    List<DepartmentResponseDto> departmentDtos =departmentPage.getContent().stream().map(department ->modelMapper.map(department, DepartmentResponseDto.class)).toList();
		List<Department> departments = departmentPage.getContent();
		List<DepartmentResponseDto> departmentDtos = new ArrayList<>();
		for (Department department : departments) {
			DepartmentResponseDto dto = modelMapper.map(department, DepartmentResponseDto.class);
			departmentDtos.add(dto);
		}
		// Populate PageResponseDto
		PageResponseDto<DepartmentResponseDto> response = new PageResponseDto<>();

		response.setContent(departmentDtos);
		response.setPageNumber(departmentPage.getNumber());
		response.setPageSize(departmentPage.getSize());
		response.setTotalElements(departmentPage.getTotalElements());
		response.setTotalPages(departmentPage.getTotalPages());
		response.setLastPage(departmentPage.isLast());

		return response;
	}

	@Override
	public DepartmentResponseDto getDepartmentById(Long id) {
		 log.info("Executing service operation: Read Department by ID {}", id);
		Department existingDepartment = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
		return modelMapper.map(existingDepartment, DepartmentResponseDto.class);
	}

	

	@Override
	public void deleteDepartment(Long id) {
		log.info("Executing service operation: Delete Department ID {}", id);
		departmentRepository.deleteById(id);
	}
	@Override
	@Transactional
	public DepartmentResponseDto updateDepartment(Long id, DepartmentRequestDto departmentRequestDto) {
		 log.info("Executing service operation: Update Department ID {}", id);
		// Find existing department
		Department existingDepartment = departmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));

		// Check duplicate department name
		if (!existingDepartment.getDepartmentName().equalsIgnoreCase(departmentRequestDto.getDepartmentName())
				&& departmentRepository.existsByDepartmentName(departmentRequestDto.getDepartmentName())) {

			throw new DuplicateResourceException(
					"Department already exists with name: " + departmentRequestDto.getDepartmentName());
		}

		// Validate employee emails
		validateEmployeeEmailsForUpdate(departmentRequestDto.getEmployees(), id.intValue());

		// Update department fields
		existingDepartment.setDepartmentName(departmentRequestDto.getDepartmentName());

		existingDepartment.setLocation(departmentRequestDto.getLocation());

		// Clear old employees
		existingDepartment.getEmployees().clear();

		// Add updated employees
		List<Employee> updatedEmployees = new ArrayList<>();

		for (EmployeeRequestDto employeeDto : departmentRequestDto.getEmployees()) {

			Employee employee = modelMapper.map(employeeDto, Employee.class);

			employee.setDepartment(existingDepartment);

			updatedEmployees.add(employee);
		}

		existingDepartment.setEmployees(updatedEmployees);

		// Save
		Department savedDepartment = departmentRepository.save(existingDepartment);

		return modelMapper.map(savedDepartment, DepartmentResponseDto.class);
	}

	private void validateEmployeeEmailsForUpdate(List<EmployeeRequestDto> employeeDtos, long i) {
		for (EmployeeRequestDto dto : employeeDtos) {
			if (employeeRepository.existsByEmail(dto.getEmail())) {
				boolean targetBelongsToCurrentDept = departmentRepository.findById(i).map(
						dept -> dept.getEmployees().stream().anyMatch(emp -> emp.getEmail().equals(dto.getEmail())))
						.orElse(false);

				if (!targetBelongsToCurrentDept) {
					throw new DuplicateResourceException(
							"Employee email already exists outside current department: " + dto.getEmail());
				}
			}
		}
	}


}
