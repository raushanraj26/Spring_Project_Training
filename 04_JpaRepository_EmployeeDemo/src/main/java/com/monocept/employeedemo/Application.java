package com.monocept.employeedemo;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.monocept.employeedemo.model.Employee;
import com.monocept.employeedemo.repository.EmployeeRepository;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(EmployeeRepository employeerepository) {

		return runner -> {
			insertEmployee(employeerepository);
//			readEmployeeById(employeerepository);
//		readAllEmployeeByName(employeerepository);
//			updateEmployeeById(employeerepository);
//			deleteEmployee(employeerepository);

			
			
		};
		}
	
	private void deleteEmployee(EmployeeRepository employeerepository) {
		// TODO Auto-generated method stub
		Employee emp = employeerepository.findById(1).orElse(null);
		employeerepository.delete(emp);
		
		
		
	}
	private void updateEmployeeById(EmployeeRepository employeerepository) {
		Employee emp = employeerepository.findById(1).orElse(null);

	    if(emp != null) {
	        emp.setName("Rahul");
	        emp.setSalary(50000.0);

	        employeerepository.save(emp);
		
	}
	}
	private void readAllEmployeeByName(EmployeeRepository employeerepository) {
	List<Employee> st=employeerepository.findByName("Raushan");
	for(Employee s:st) {
		System.out.println(s);
	}
		
	}
	private void readEmployeeById(EmployeeRepository employeerepository) {
		
		Optional<Employee> f=employeerepository.findById(1);
		System.out.println(f);
	}
	private void insertEmployee(EmployeeRepository employeerepository) {
		System.out.println("Saving in db...");
//		employeerepository.save(1,"Raushan","Mzp",842001,54000,21,ra@gmail.com);
		Employee e=new Employee(2,"Raushan","Mzpp","842001",54000.0,21,"RAushan@gmail.com");
		employeerepository.save(e);
		
		System.out.println("Successfully inserted!!");
		
	}
	
	

}
