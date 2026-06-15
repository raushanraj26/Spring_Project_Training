package com.monocept.DepartmentEmployeeManagement.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentResponseDto {

    private Long id;

    @JsonProperty("department_name")
    private String departmentName;

    private String location;

    private List<EmployeeResponseDto> employees;
}
