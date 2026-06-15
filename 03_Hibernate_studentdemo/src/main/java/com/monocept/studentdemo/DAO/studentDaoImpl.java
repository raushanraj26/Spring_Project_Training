package com.monocept.studentdemo.DAO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monocept.studentdemo.model.Student;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class studentDaoImpl implements StudentDAO {

	private EntityManager entityManager;
	@Autowired
	public studentDaoImpl(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	@Override
	@Transactional  //jab db me chnge hoga save ke karan then use otherwise not use for read only
	public void save(Student thestudent) {
		// TODO Auto-generated method stub
		entityManager.persist(thestudent);   //for save in db we use persist method
		
	}
	@Override
	public Student findById(int id) {
		
		return entityManager.find(Student.class, id);
	}

}
