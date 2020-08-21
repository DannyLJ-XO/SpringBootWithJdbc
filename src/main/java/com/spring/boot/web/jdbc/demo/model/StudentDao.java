package com.spring.boot.web.jdbc.demo.model;


import java.util.List;


public interface StudentDao {

	public int create(Student student);

	public List<Student> read();

	public Student findStudentById(int studentId);

	public int update(Student student);

	public int delete(int studentId);

	public List<Student> findAllStudents();

}
