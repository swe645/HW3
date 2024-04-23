package com.example.hw3.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation. PostMapping; 
import org.springframework.web.bind.annotation. PutMapping; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation. RequestMapping; 
import org.springframework.web.bind.annotation. RestController;
import com.example.hw3.model.studentsurvey;
import com.example.hw3.service.StudentService;

@RestController
@RequestMapping("/api/students") //defines the base url for all apis

public class StudentController {

	private StudentService studentService;
	//inject studentService object using constructor based dependency injection 
	public StudentController (StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	
	//Build create student api that handles post requests
	@PostMapping
	public ResponseEntity<studentsurvey> saveStudent (@RequestBody studentsurvey studentsurveydata) {
		System.out.println(studentsurveydata.toString());
		return new ResponseEntity<studentsurvey>(studentService.saveStudent (studentsurveydata), HttpStatus.CREATED);
	}
	
	//Create get all students REST API
	@GetMapping
	public List<studentsurvey> getAllStudents() {
		System.out.println("Hello");
		return studentService.getAllStudents();
	}
	
	//create get student by id REST API.
	@GetMapping("/{id}")
	public ResponseEntity<studentsurvey> getStudentById(@PathVariable("id") long studentId) {
		return new ResponseEntity<studentsurvey>(studentService.getStudentById(studentId), HttpStatus.OK);
	}
	
	//Now to implement update Student REST API
	@PutMapping("/{id}")
	public studentsurvey updateStudent (@PathVariable long id, @RequestBody studentsurvey studentsurveydata){ 
		return studentService.updateStudent (studentsurveydata, id);
	}
	
	// Create delete student REST API.
	@DeleteMapping("/{id}")
	public String deleteStudent (@PathVariable("id") long id){
		//delete student from database
		studentService.deleteStudent(id);
		return ("Survey deleted successfully edited to check jenkins.");
	}
	
}
	
	








