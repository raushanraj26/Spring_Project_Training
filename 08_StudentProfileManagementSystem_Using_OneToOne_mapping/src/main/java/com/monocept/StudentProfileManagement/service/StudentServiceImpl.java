package com.monocept.StudentProfileManagement.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monocept.StudentProfileManagement.dto.PageResponseDto;
import com.monocept.StudentProfileManagement.dto.StudentRequestDto;
import com.monocept.StudentProfileManagement.dto.StudentResponseDto;
import com.monocept.StudentProfileManagement.entity.Student;
import com.monocept.StudentProfileManagement.entity.StudentProfile;
import com.monocept.StudentProfileManagement.repository.StudentProfileRepository;
import com.monocept.StudentProfileManagement.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {
	private StudentRepository studentrepository;
	private StudentProfileRepository studentprofilerepository;
//	previous project me manually convert kr rhe the dto <-> Entity,bt now we are using ModelMapper
	private ModelMapper modelmapper;

//constructor injection
	@Autowired
	public StudentServiceImpl(StudentRepository studentrepository, StudentProfileRepository studentprofilerepository,
			ModelMapper modelmapper) {
		this.studentrepository = studentrepository;
		this.studentprofilerepository = studentprofilerepository;
		this.modelmapper = modelmapper;
	}

	@Override
	public StudentResponseDto createStudent(StudentRequestDto requestDto) {
		// Check duplicate email
	    if (studentprofilerepository.existsByEmail(
	            requestDto.getProfile().getEmail())) {

	        throw new RuntimeException("Email already exists");
	    }

	    // DTO -> Entity
	    Student student = modelmapper.map(
	            requestDto,
	            Student.class);

	    StudentProfile profile = modelmapper.map(
	            requestDto.getProfile(),
	            StudentProfile.class);

	    // Maintain both sides of one-to-one mapping
	    student.setProfile(profile);
	    profile.setStudent(student);

	    // Save student (profile will also save because of CascadeType.ALL)
	    Student savedStudent = studentrepository.save(student);

	    // Entity -> Response DTO
	    return modelmapper.map(
	            savedStudent,
	            StudentResponseDto.class);
	}

	@Override
	public List<StudentResponseDto> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentResponseDto getStudentById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentResponseDto updateStudent(Long id, StudentRequestDto requestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteStudent(Long id) {
		// TODO Auto-generated method stub

	}

}
