package com.example.examapp.service;

import com.example.examapp.dao.ExamDAO;
import com.example.examapp.dao.QuestionDAO;
import com.example.examapp.model.Exam;
import com.example.examapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    ExamDAO examDAO;

    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createExam(String category, int numQ, String title) {
        List<Question> questions = questionDAO.findRandomQuestionByCategory(category, numQ);

        Exam exam = new Exam();
        exam.setExamTitle(title);
        exam.setQuestions(questions);
        examDAO.save(exam);

        return new ResponseEntity<>("Successful exam generation", HttpStatus.CREATED);
    }
}
