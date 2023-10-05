package com.example.examapp.service;

import com.example.examapp.dao.ExamDAO;
import com.example.examapp.dao.QuestionDAO;
import com.example.examapp.model.Exam;
import com.example.examapp.model.Question;
import com.example.examapp.model.QuestionWrapper;
import com.example.examapp.model.Response;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getExamQuestions(Integer id) {
        Optional<Exam> exam = examDAO.findById(id);
        List<Question> questionFromDB = exam.get().getQuestions();
        List<QuestionWrapper> questionsForStudent = new ArrayList<>();
        for(Question q : questionFromDB) {
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForStudent.add(qw);
        }


        return new ResponseEntity<>(questionsForStudent, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> response) {
        Exam exam = examDAO.findById(id).get();
        List<Question> questions = exam.getQuestions();
        int correct = 0;
        int i = 0;
        for(Response responses : response) {
            if(responses.getResponse().equals(questions.get(i).getCorrectAnswer()))
                correct++;

            i++;
        }
        return new ResponseEntity<>(correct, HttpStatus.OK);
    }
}
