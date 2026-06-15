package com.monocept.hibernate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.monocept.hibernate.DAO.EmployeeDao;
import com.monocept.hibernate.model.Employee;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeDao employeedao) {

		return runner -> {

			//createEmployee(employeedao);

			readAllEmployees(employeedao);

//			readEmployeeById(employeedao);

//			readEmployeeByName(employeedao);

//			readEmployeeByAge(employeedao);

//			readEmployeeByCityCode(employeedao);

//			salaryGreaterThan(employeedao);

//			updateEmployee(employeedao);

//			deleteEmployee(employeedao);

		};
	}

	
	
	// INSERT
	private void createEmployee(EmployeeDao employeedao) {

		System.out.println("Creating employee object");

		Employee emp = new Employee();

		emp.setEmpName("Rahul");
		emp.setEmpCity("Mumbai");
		emp.setCityCode("MUM01");
		emp.setEmpSalary(25000);
		emp.setEmpAge(24);
		emp.setEmpEmail("rahul@gmail.com");

		System.out.println("Saving employee");

		employeedao.saveEmployee(emp);

		System.out.println("Employee saved");
	}

	
	
	// READ ALL
	private void readAllEmployees(EmployeeDao employeedao) {

		System.out.println("Fetching all employees");

		System.out.println(
				employeedao.getAllEmployees());
	}

	
	
	// READ BY ID
	private void readEmployeeById(EmployeeDao employeedao) {

		System.out.println("Fetching employee by id");

		System.out.println(
				employeedao.getEmployeeById(1));
	}

	
	
	// READ BY NAME
	private void readEmployeeByName(EmployeeDao employeedao) {

		System.out.println("Fetching employee by name");

		System.out.println(
				employeedao.getEmployeeByName("Rahul"));
	}

	
	
	// READ BY AGE
	private void readEmployeeByAge(EmployeeDao employeedao) {

		System.out.println("Fetching employee by age");

		System.out.println(
				employeedao.getEmployeeByAge(24));
	}

	
	
	// READ BY CITY CODE
	private void readEmployeeByCityCode(EmployeeDao employeedao) {

		System.out.println("Fetching employee by city code");

		System.out.println(
				employeedao.getEmployeeByCityCode("MUM01"));
	}

	
	
	// SALARY > 10000
	private void salaryGreaterThan(EmployeeDao employeedao) {

		System.out.println("Fetching employees salary > 10000");

		System.out.println(
				employeedao.getEmployeeSalaryGreaterThan(10000));
	}

	
	
	// UPDATE
	private void updateEmployee(EmployeeDao employeedao) {

		Employee emp =
				employeedao.getEmployeeById(1);

		emp.setEmpSalary(50000);

		employeedao.updateEmployee(emp);

		System.out.println("Employee updated");
	}

	
	
	// DELETE
	private void deleteEmployee(EmployeeDao employeedao) {

		employeedao.deleteEmployee(1);

		System.out.println("Employee deleted");
	}
}