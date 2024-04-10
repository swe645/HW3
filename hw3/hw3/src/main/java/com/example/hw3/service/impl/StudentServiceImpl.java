package com.example.hw3.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hw3.exception.ResourceNotFoundException;
import com.example.hw3.model.studentsurvey;
import com.example.hw3.repository.StudentRepository;
import com.example.hw3.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	public StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}
	
	
	@Override
	public studentsurvey saveStudent (studentsurvey studentsurvey) {
		return studentRepository.save(studentsurvey);
	}
	
	@Override
	public List<studentsurvey> getAllStudents() {
		return studentRepository.findAll();
	}
	
	@Override
	public studentsurvey getStudentById(long id) {
		//Please note that repository's findById() method returns a Optional object (of java.util) 
		//which is a generic, hence we need to pass the type Student to the returned Optional object 
		Optional<studentsurvey> studentsurvey = studentRepository.findById(id);
		
		//Check if the returned Optional object contains the student, if so, return the student object 
		if (studentsurvey.isPresent()){
			return studentsurvey.get(); // here get() method returns the content of the Optional object.
		}
		else {
			//this internally creates a message. Check the ResourceNotFoundException for details. 
			throw new ResourceNotFoundException("Student", "Id", id);

		}
		//Above code can be replaced by one line code with lambda expresssion
		// return studentRepository.findById(id).orElse Throw(()->new ResourceNotFoundException("Employee", "Id", id));
	}
	
	@Override
	public studentsurvey updateStudent (studentsurvey studentsurvey, long id) {
		//check whether student with given id exists in database
		studentsurvey existingStudentSurvey = studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Student", "Id", id));
		existingStudentSurvey.setFirstName(studentsurvey.getFirstName()); 
		existingStudentSurvey.setLastName(studentsurvey.getLastName()); 
		existingStudentSurvey.setEmail(studentsurvey.getEmail());
		//save existing student to database studentRepository.save(existingStudent);
		return existingStudentSurvey; //return the existing student to the controller layer
	}
	
	
	@Override
	public void deleteStudent (long id) {
		//check whether the student exists in the database
		studentRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Student", "Id", id));
		studentRepository.deleteById(id);
	}
}
	