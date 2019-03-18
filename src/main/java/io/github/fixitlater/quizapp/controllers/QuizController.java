package io.github.fixitlater.quizapp.controllers;


import io.github.fixitlater.quizapp.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class QuizController {

    private QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }


    @GetMapping("quiz/menu")
    public String getQuizMenu(Model model) {
        model.addAttribute("categories", quizService.getCategoryList());
        model.addAttribute("languages", quizService.getLanguageList());
        quizService.getLanguageList().forEach(a-> System.out.println(a));
        return "quiz/quizMenu";
    }

    @PostMapping("/quiz/challenge")
    public String showParamQuiz(@RequestParam String category, @RequestParam String lang) {
        System.out.println(category + " " + lang);
        return "index";
    }

    @GetMapping("/quiz/test")
    public String showCategories(Model model) {

        return "index";
    }

}
