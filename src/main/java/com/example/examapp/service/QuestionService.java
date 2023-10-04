package com.example.examapp.service;

import com.example.examapp.dao.QuestionDAO;
import com.example.examapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;


    public ResponseEntity<Optional<Question>> findQuestionById(Integer id) {
        try {
            return new ResponseEntity<>(questionDAO.findById(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(questionDAO.findById(null), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getallQuestions() {
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addNewQuestion(Question newQuestion) {
        questionDAO.save(newQuestion);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<String> removeQuestion(Integer id) {
        try {
            if (questionDAO.findById(id).isPresent()) {
                questionDAO.deleteById(id);
                return new ResponseEntity<>("Deleted succesfully!", HttpStatus.OK);
            }
        }catch (Exception e) {
                e.printStackTrace();
            }

        return new ResponseEntity<>("Not successful deletion", HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Question>> getQuestionByCategory(String category){
       try {
           return new ResponseEntity<>(questionDAO.findByCategory(category), HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
