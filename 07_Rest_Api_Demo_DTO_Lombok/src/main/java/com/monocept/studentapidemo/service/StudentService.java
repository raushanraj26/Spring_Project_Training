package com.monocept.studentapidemo.service;

import java.util.List;
import java.util.Map;

import com.monocept.studentapidemo.dto.PageResponseDto;
import com.monocept.studentapidemo.dto.StudentRequestDto;
import com.monocept.studentapidemo.dto.StudentResponseDto;
import com.monocept.studentapidemo.entity.Student;

public interface StudentService {
	
	public StudentResponseDto createStudent(StudentRequestDto student);
	public PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber,int pageSize);
	public void validateStudent(StudentRequestDto studentrequestdto);
	
	
	
	
	
	
	
	
	
	
	
//	ye sabme Student entity ko request and response me show kr rhe hai 
	
//	public Student createStudent(Student student);
//
//	public List<Student> createMultipleStudent(List<Student> students);
//
//	public Student FindById(int id);
//
//	public Student updateStudent(int id, Student updatedstudent);
//
//	public Student updatePartially(int id, Map<String, Object> updatedData);
//
//	public void deleteStudent(int id);

}
