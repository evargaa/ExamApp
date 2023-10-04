package com.example.examapp.controller;

import com.example.examapp.model.Question;
import com.example.examapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;
    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> AllQuestions() {
        return questionService.getallQuestions();
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question newQuestion) {
       return questionService.addNewQuestion(newQuestion);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeQuestion(@PathVariable Integer id) {
        return questionService.removeQuestion(id);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category) {
        return questionService.getQuestionByCategory(category);
    }
}