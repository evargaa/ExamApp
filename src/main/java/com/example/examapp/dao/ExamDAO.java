package com.example.examapp.dao;

import com.example.examapp.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamDAO extends JpaRepository<Exam, Integer> {
}
