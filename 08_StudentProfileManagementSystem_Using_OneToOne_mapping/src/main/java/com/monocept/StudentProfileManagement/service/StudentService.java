package com.monocept.StudentProfileManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.monocept.StudentProfileManagement.dto.PageResponseDto;
import com.monocept.StudentProfileManagement.dto.StudentRequestDto;
import com.monocept.StudentProfileManagement.dto.StudentResponseDto;

public interface StudentService {

	StudentResponseDto createStudent(StudentRequestDto requestDto);

	List<StudentResponseDto> getAllStudents();

	PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber, int pageSize);

	StudentResponseDto getStudentById(Long id);

	StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto);

	void deleteStudent(Long id);
}