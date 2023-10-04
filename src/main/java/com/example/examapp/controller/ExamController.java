package com.example.examapp.controller;

import com.example.examapp.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exam/")
public class ExamController {

@Autowired
    ExamService examService;


@PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,
                                             @RequestParam int numQ, @RequestParam String title) {
        return examService.createExam(category, numQ, title);
    }
}