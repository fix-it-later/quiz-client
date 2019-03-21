package io.github.fixitlater.quizapp.controllers;


import io.github.fixitlater.quizapp.dtos.QuestionDto;
import io.github.fixitlater.quizapp.services.QuestionService;
import io.github.fixitlater.quizapp.services.QuizService;
import io.github.fixitlater.quizapp.services.QuizStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;


@Controller
public class QuizController {


    private QuizService quizService;
    private QuestionService questionService;
    private QuizStorage quizStorage;

    @Autowired
    public QuizController(QuizService quizService, QuestionService questionService, QuizStorage quizStorage) {
        this.quizService = quizService;
        this.questionService = questionService;
        this.quizStorage = quizStorage;
    }


    @GetMapping("/quiz/menu")
    public String getQuizMenu(Model model) {
        model.addAttribute("categories", quizService.getCategoryList());
        model.addAttribute("languages", quizService.getLanguageList());
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

    @GetMapping("/quiz/quizAttempt")
    public String attemptQuiz() {
        return "quiz/quizAttempt";
    }

    @GetMapping("/quiz/randomQuestion")
    public String showRandomQuestion(Model model) {
        QuestionDto questionDto = questionService.getRandomQuestion();
        model.addAttribute("question", questionDto);
        return "quiz/randomQuestion";
    }

    @GetMapping("/quiz/Questions")
    public String showQuestions(Model model) {
        QuestionDto[] questionDtos = questionService.getAllQuestions();
        String uid = quizStorage.addToQuizMap(questionDtos);
        model.addAttribute("questions", Arrays.asList(questionDtos));
        model.addAttribute("uid", uid);
        return "quiz/allQuestions";
    }

    @PostMapping("/quiz/results/{uid}")
    public String evaluateAnswers(@RequestParam Map<String, String> allParameters,@PathVariable String uid, Model model)
    {
        QuestionDto [] questionDtos = quizStorage.getQuiz(uid);
        int correctAnswers = questionService.evaluateAnswers(allParameters, questionDtos);
        model.addAttribute("correctAnswers", correctAnswers);
        return "quiz/evaluate";
    }
}
