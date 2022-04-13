package com.example.quizapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class QuizApiController {

    private List<Quiz> quizzes = new ArrayList<>();
    private QuizFileDao quizFileDao = new QuizFileDao();

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

        // 回答が正しいかどうかチェックして、結果を返却する
        // 指定されたquestionを登録済みのクイズから検索する
        for(Quiz quiz: quizzes) {
            // もしクイズが見つかったら
            if(quiz.getQuestion().equals(question)) {
                // 登録されているanswerと回答として渡ってきたanswerが一致している場合、「正解」と返却する
                if(quiz.isAnswer() == answer) {
                    return "正解！";
                } else {
                    // もし一致していなければ「不正解」と返却する
                    return "不正解！";
                }
            }
        }

        // クイズが見つからなかった場合は、「問題がありません」と返却する
        return "問題がありません";
    }

    @PostMapping("/save")
    public String save() {
        try {
            quizFileDao.write(quizzes);
            return "ファイルに保存しました";
        } catch (IOException e) {
            e.printStackTrace();
            return "ファイルの保存に失敗しました";
        }
    }
}
