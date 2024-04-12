package com.example.hw3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hw3.model.studentsurvey;

public interface StudentRepository extends JpaRepository<studentsurvey, Long> {
	
	
}