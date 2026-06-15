package com.monocept.studentapidemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monocept.studentapidemo.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
