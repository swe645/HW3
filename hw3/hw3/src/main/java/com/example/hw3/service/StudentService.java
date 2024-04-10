package com.example.hw3.service;
import java.util.List;
import com.example.hw3.model.studentsurvey;

public interface StudentService { 
	studentsurvey saveStudent (studentsurvey studentsurvey); 
	List<studentsurvey> getAllStudents();
	studentsurvey getStudentById(long id);
	studentsurvey updateStudent (studentsurvey studentsurvey, long id); 
	void deleteStudent (long id);
}