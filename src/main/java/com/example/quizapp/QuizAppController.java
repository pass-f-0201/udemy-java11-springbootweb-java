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

    // 引数 String型 question, boolean型 answer（正解）
    @PostMapping("/create")
    public void create(@RequestParam String question, @RequestParam boolean answer) {

        Quiz quiz = new Quiz(question, answer);
        quizzes.add(quiz);
    }

    // 引数 String型 question（質問文）, boolean型 answer（回答）
    @GetMapping("/check")
    public String check(@RequestParam String question,@RequestParam  boolean answer) {

        // TODO:回答が正しいかどうかチェックして、結果を返却する
        // 指定されたquestionを登録済みのクイズから検索する
        for(Quiz quiz: quizzes) {
            // もしクイズが見つかったら
            if(quiz.getQuestion().equals(question)) {
                return "見つかった!" + quiz.getQuestion();
            }
        }

        // クイズが見つからなかった場合は、「問題がありません」と返却する
        return "問題がありません";
    }

}
