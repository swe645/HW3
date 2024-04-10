package com.example.hw3.service;
import java.util.List;
import com.example.hw3.model.studentsurvey;

public interface StudentService { 
	studentsurvey saveStudent (studentsurvey studentsurveydata); 
	List<studentsurvey> getAllStudents();
	studentsurvey getStudentById(long id);
	studentsurvey updateStudent (studentsurvey studentsurveydata, long id); 
	void deleteStudent (long id);
}