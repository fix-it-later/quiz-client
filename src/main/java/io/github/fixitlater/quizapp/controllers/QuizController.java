package io.github.fixitlater.quizapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuizController {

    @GetMapping ("quiz/menu")
    public String getQuizMenu (){
        return "quiz/quizMenu";
    }

}
