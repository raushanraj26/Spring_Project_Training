package com.monocept.hibernate.DAO;



import java.util.List;

import com.monocept.hibernate.model.Employee;

public interface EmployeeDao {

    // CREATE
    Employee saveEmployee(Employee employee);

    // READ ALL
    List<Employee> getAllEmployees();

    // READ BY ID
    Employee getEmployeeById(int id);

    // READ BY NAME
    List<Employee> getEmployeeByName(String name);

    // READ BY AGE
    List<Employee> getEmployeeByAge(int age);

    // READ BY CITY CODE
    List<Employee> getEmployeeByCityCode(String cityCode);

    // SALARY > 10000
    List<Employee> getEmployeeSalaryGreaterThan(double salary);

    // UPDATE
    Employee updateEmployee(Employee employee);

    // DELETE
    void deleteEmployee(int id);
}
