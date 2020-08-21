package com.spring.boot.web.jdbc.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class StudentDaoImpl implements StudentDao {
 
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public int create(Student student) {
 
		String sql = "insert into student(stu_name,stu_email,stu_course) values(?,?,?)";
 
		try {
			int counter = jdbcTemplate.update(sql,
			new Object[] { student.getName(), student.getEmail(), student.getCourse() });
			return counter;
 
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
 
	@Override
	public List<Student> read() {
		List<Student> studentList = jdbcTemplate.query("SELECT * FROM student", new RowMapper<Student>() {
 
			@Override
			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				Student student = new Student();
				student.setId(rs.getInt("stu_id"));
				student.setName(rs.getString("stu_name"));
				student.setEmail(rs.getString("stu_email"));
				student.setCourse(rs.getString("stu_course"));
				return student;
			}
		});
		return studentList;
	}
 
	@Override
	public Student findStudentById(int studentId) {
 
		List<Student> studentList = jdbcTemplate.query("SELECT * FROM student where stu_id=?",new Object[] { studentId }, new RowMapper<Student>() {
				@Override
				public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
				   Student student = new Student();
				   student.setId(rs.getInt("stu_id"));
			   student.setName(rs.getString("stu_name"));
				   student.setEmail(rs.getString("stu_email"));
				   student.setCourse(rs.getString("stu_course"));
 
				return student;
					}
 
				});
 
		return studentList.get(0);
	}
 
	@Override
	public int update(Student student) {
		String sql = "update  student set stu_name=?, stu_email=?, stu_course=? where stu_id=?";
 
		try {
 
			int counter = jdbcTemplate.update(sql,new Object[] { student.getName(), student.getEmail(), student.getCourse(), student.getId() });
			return counter;
 
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
 
	@Override
	public int delete(int studentId) {
		String sql = "delete from student where stu_id=?";
 
		try {
 
		int counter = jdbcTemplate.update(sql, new Object[] { studentId });
return counter;
 
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public List<Student> findAllStudents() {
		
		return read();
	}
 
}

