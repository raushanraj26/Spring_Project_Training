package com.monocept.StudentProfileManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.StudentProfileManagement.entity.Student;



public interface StudentRepository extends JpaRepository<Student, Long> {

}