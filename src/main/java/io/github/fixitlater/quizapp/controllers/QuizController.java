package io.github.fixitlater.quizapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class QuizController {

    @GetMapping ("quiz/menu")
    public String getQuizMenu (){
        return "quiz/quizMenu";
    }

    @PostMapping("/quiz/challenge ")
    public String showParamQuiz(@RequestParam String category, @RequestParam String lang){
        System.out.println(category + " " + lang);
        return "index";
    }

}
