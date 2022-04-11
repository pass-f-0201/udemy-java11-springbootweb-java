package com.example.quizapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizAppController {

    private List<Quiz> quizzes = new ArrayList<>();

    // 引数 なし
    @GetMapping("/show")
    public List<Quiz> show() {
        return quizzes;
    }


    
    // 引数 String型 question, boolean型 answer
    @PostMapping("/create")
    public void create(@RequestParam String question, @RequestParam boolean answer) {

        Quiz quiz = new Quiz(question, answer);
        quizzes.add(quiz);
    }

}
