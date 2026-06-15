package com.monocept.studentapidemo.exception;


public class StudentNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StudentNotFoundException(int id) {
		super("Student Not found with id: "+id);
	}
	

}
