package com.monocept.studentapidemo.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.monocept.studentapidemo.dto.PageResponseDto;
import com.monocept.studentapidemo.dto.StudentRequestDto;
import com.monocept.studentapidemo.dto.StudentResponseDto;
import com.monocept.studentapidemo.entity.Student;
import com.monocept.studentapidemo.exception.StudentNotFoundException;
import com.monocept.studentapidemo.repository.StudentRepository;

@Service
public class StudentServiceImplementation implements StudentService {
	private StudentRepository studentrepository;
//	previous project me manually convert kr rhe the dto <-> Entity,bt now we are using ModelMapper
	private ModelMapper modelmapper;

//constructor injection
	@Autowired
	public StudentServiceImplementation(StudentRepository studentrepository, ModelMapper modelmapper) {
		this.studentrepository = studentrepository;
		this.modelmapper = modelmapper;
	}

	@Override
	public StudentResponseDto createStudent(StudentRequestDto student) {
  Student s=modelmapper.map(student, Student.class);
  //repository hmesa entity class object return krta hai
  Student savedstudent=studentrepository.save(s);
  return modelmapper.map(savedstudent,StudentResponseDto.class);
	}

	@Override
	public PageResponseDto<StudentResponseDto> getAllStudentsWithPagination(int pageNumber, int pageSize) {
		PageRequest pageable=PageRequest.of(pageNumber, pageSize);
		Page<Student> studentpage=studentrepository.findAll(pageable);
		List<Student> students =studentpage.getContent();
		List<StudentResponseDto> studentresponses=new ArrayList<>();
		for(Student s:students) {
			StudentResponseDto studentresponse=modelmapper.map(s,StudentResponseDto.class);
			studentresponses.add(studentresponse);
		}
		PageResponseDto<StudentResponseDto> pageresponsedto=new PageResponseDto<>();
		pageresponsedto.setContent(studentresponses);
		pageresponsedto.setPageNumber(studentpage.getNumber());
		pageresponsedto.setPageSize(studentpage.getSize());
		pageresponsedto.setTotalCount(studentpage.getTotalElements());
		pageresponsedto.setLastpage(studentpage.isLast());
		pageresponsedto.getTotalPage(studentpage.getTotalPages());
		return pageresponsedto;
		
	}

	@Override
	public void validateStudent(StudentRequestDto studentrequestdto) {
		if(studentrequestdto==null) {
			throw new IllegalArgumentException("STudent request body is required");
		}
		if(studentrequestdto.getName()==null || studentrequestdto.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Name is required");
		}
		if(studentrequestdto.getAge()<=0) {
			throw new IllegalArgumentException("Age must be greater than zero");
			
		}
		
	}
	
//---------------------------------------Student ENtity se hi operation kr rhe hai-----------
	
	
//	@Override
//	public Student createStudent(Student student) {
//		return studentrepository.save(student);
//		
//	}
//	@Override
//	public List<Student> createMultipleStudent(List<Student> students) {
//		return studentrepository.saveAll(students);
//	}
//
//	@Override
//	public Student FindById(int id) {
//		return studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//	}
//
//	@Override
//	public Student updateStudent(int id, Student updatedstudent) {
//		Student existingstudent = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//
//		existingstudent.setName(updatedstudent.getName());
//		existingstudent.setAge(updatedstudent.getAge());
//		existingstudent.setDepartment(updatedstudent.getDepartment());
//		return studentrepository.save(existingstudent);
//	}
//
//	@Override
//	public Student updatePartially(int id, Map<String, Object> updatedData) {
//		Student existingstudent = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//		// check kon kon sa field aya hai,jo aaya hai usse update kr do
//		if (updatedData.containsKey("name")) {
//			existingstudent.setName((String) updatedData.get("name"));
//
//		}
//		if (updatedData.containsKey("Age")) {
//			existingstudent.setAge((int) updatedData.get("Age"));
//
//		}
//		if (updatedData.containsKey("department")) {
//			existingstudent.setDepartment((String) updatedData.get("department"));
//
//		}
//		return studentrepository.save(existingstudent);
//	}
//
//	@Override
//	public void deleteStudent(int id) {
//		Student existingstudent = studentrepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
//		studentrepository.delete(existingstudent);
//
//	}

}
