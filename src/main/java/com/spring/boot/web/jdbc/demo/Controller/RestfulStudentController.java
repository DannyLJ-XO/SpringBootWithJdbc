package com.spring.boot.web.jdbc.demo.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.spring.boot.web.jdbc.demo.model.Student;
import com.spring.boot.web.jdbc.demo.model.StudentDao;

@RestController
@RequestMapping("/api")
public class RestfulStudentController { 
    @Autowired
    StudentDao studentService; 
     
    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public ResponseEntity<List<Student>> listAllUsers() {
        List<Student> students = studentService.findAllStudents();
        if(students.isEmpty()){
            return new ResponseEntity<List<Student>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Student>>(students, HttpStatus.OK);
    }
	
    
   @RequestMapping(value = "/read/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public ResponseEntity<Student> getStudent(@PathVariable("id") int id) {
       System.out.println("Fetching Student with id " + id);
       Student student = studentService.findStudentById(id);
       if (student == null) {
           System.out.println("Student with id " + id + " not found");
           return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<Student>(student, HttpStatus.OK);
   }
   @PostMapping(value = "/create")
   public ResponseEntity<Student> addStudent(@RequestBody Student student) {
       System.out.println("adding Student");
       int result = studentService.create(student);
       if (result == 0) {
           System.out.println("problem creating student!");
           return new ResponseEntity<Student>(HttpStatus.EXPECTATION_FAILED);
       }
       return new ResponseEntity<Student>(student, HttpStatus.OK);
   }
   
   @PutMapping(value = "/update/{id}")
   public ResponseEntity<Student> updateStudent(@PathVariable("id") int id, @RequestBody Student student) {
       System.out.println("update Student with id " + id);
       int result = studentService.update(student);
       if (result == 0) {
           System.out.println("Student with id " + id + "failed to update");
           return new ResponseEntity<Student>(HttpStatus.EXPECTATION_FAILED);
       }
       return new ResponseEntity<Student>(student, HttpStatus.OK);
   }
   @DeleteMapping(value = "/delete/{id}")
   public ResponseEntity<Student> deleteStudent(@PathVariable("id") int id) {
       System.out.println("delete Student with id " + id);
       int result = studentService.delete(id);
       if (result == 0) {
           System.out.println("Student with id " + id + "failed to delete");
           return new ResponseEntity<Student>(HttpStatus.EXPECTATION_FAILED);
       }
       return new ResponseEntity<Student>(HttpStatus.NO_CONTENT);
   }
}
