package com.monocept.studentdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.monocept.studentdemo.DAO.StudentDAO;
import com.monocept.studentdemo.model.Student;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return runner -> {
//       System.out.println("Hello");
//        	
//        };
//    }
    
    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentdao) {
        return runner -> {
//      createStudent(studentdao);
        	getstudentById(studentdao);
        	
        };
    }
    
//    2.Read operation
private void getstudentById(StudentDAO studentdao) {
	System.out.println("Finding student by Id...");
		Student s=studentdao.findById(1);
		
		if(s!=null) {
			System.out.println("ID is: " +s.getid() +", Name is: "+s.getname());
			System.out.println("Found the Student!!!");
			
		}else {
			System.out.println("Not Found!!!");
		}
		
		
	}

	//1.create or insert
	private void createStudent(StudentDAO studentdao) {
		System.out.println("Creating new student object: ");
		Student tempstudent=new Student(1,"Raushan");
		
		System.out.println("saving student in db/record");
		studentdao.save(tempstudent);
		
		System.out.println("Saved student.Generated id is "+tempstudent.getid());
		
	}
}