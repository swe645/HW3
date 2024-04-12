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
	public studentsurvey saveStudent (studentsurvey studentsurveydata) {
		return studentRepository.save(studentsurveydata);
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
	public studentsurvey updateStudent(studentsurvey studentsurveydata, long id) {
	    // Check whether student with given id exists in database
	    studentsurvey existingStudentSurvey = studentRepository.findById(id).orElseThrow(
	            () -> new ResourceNotFoundException("Student", "Id", id));
	    System.out.println(existingStudentSurvey);
	    // Update fields only if studentsurveydata has non-null values
	    if (studentsurveydata.getFirstName() != "") {
	        existingStudentSurvey.setFirstName(studentsurveydata.getFirstName());
	    }
	    if (studentsurveydata.getLastName() != "") {
	        existingStudentSurvey.setLastName(studentsurveydata.getLastName());
	    }
	    if (studentsurveydata.getStreetAddress() != "") {
	        existingStudentSurvey.setStreetAddress(studentsurveydata.getStreetAddress());
	    }
	    if (studentsurveydata.getCity() != "") {
	        existingStudentSurvey.setCity(studentsurveydata.getCity());
	    }
	    if (studentsurveydata.getState() != "") {
	        existingStudentSurvey.setState(studentsurveydata.getState());
	    }
	    if (studentsurveydata.getZip() != "") {
	        existingStudentSurvey.setZip(studentsurveydata.getZip());
	    }
	    if (studentsurveydata.getTelephoneNumber() != "") {
	        existingStudentSurvey.setTelephoneNumber(studentsurveydata.getTelephoneNumber());
	    }
	    if (studentsurveydata.getEmail() != "") {
	        existingStudentSurvey.setEmail(studentsurveydata.getEmail());
	    }
	    if (studentsurveydata.getDateOfSurvey() != null) {
	        existingStudentSurvey.setDateOfSurvey(studentsurveydata.getDateOfSurvey());
	    }
	    if (studentsurveydata.getLikedMost() != "") {
	        existingStudentSurvey.setLikedMost(studentsurveydata.getLikedMost());
	    }
	    if (studentsurveydata.getInterestSource() != "") {
	        existingStudentSurvey.setInterestSource(studentsurveydata.getInterestSource());
	    }
	    if (studentsurveydata.getLikelihoodRecommendation() != "") {
	        existingStudentSurvey.setLikelihoodRecommendation(studentsurveydata.getLikelihoodRecommendation());
	    }

	    // Save the updated student survey to the database
	    final studentsurvey updatedSurvey = studentRepository.save(existingStudentSurvey);
	    System.out.println(updatedSurvey);
	    return updatedSurvey; // Return the updated student survey to the controller layer
	}

	
	
	@Override
	public void deleteStudent (long id) {
		//check whether the student exists in the database
		studentRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Student", "Id", id));
		studentRepository.deleteById(id);
	}
}
	
