package com.monocept.DepartmentEmployeeManagement.dto;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeResponseDto {

    private Long id;

    @JsonProperty("employee_name")
    private String employeeName;

    private String email;

    private Double salary;
}