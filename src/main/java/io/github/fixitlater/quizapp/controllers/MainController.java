package io.github.fixitlater.quizapp.controllers;

import io.github.fixitlater.quizapp.dtos.QuestionDto;
import io.github.fixitlater.quizapp.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private RestTemplate restTemplate;
    private QuestionService questionService;
    private QuestionDto[] questionDtos;

    @Autowired
    public MainController(RestTemplateBuilder restTemplateBuilder, QuestionService questionService) {
        this.restTemplate = restTemplateBuilder.build();
        this.questionService = questionService;
    }

    @GetMapping (value = {"/","/index","/home"})
    public String homePage(){
        return "index";
    }

    @GetMapping("/quiz/randomQuestion")
    public String showRandomQuestion(Model model){
        QuestionDto questionDto = restTemplate.getForObject("http://fix-it-later-quiz-api.herokuapp.com/questions/random", QuestionDto.class);

        model.addAttribute("question", questionDto);
        return "quiz/randomQuestion";
    }

    @GetMapping("/quiz/Questions")
    public String showQuestions(Model model){
        questionDtos = restTemplate.getForObject("http://fix-it-later-quiz-api.herokuapp.com/questions/all", QuestionDto[].class);

        List<QuestionDto> questions = Arrays.asList(questionDtos);
        model.addAttribute("questions", questions);
        return "quiz/allQuestions";
    }

    @PostMapping("/quiz/results")
    public String evaluateAnswers(@RequestParam Map<String, String> allParameters, Model model){
        int correctAnswers = questionService.evaluateAnswers(allParameters, questionDtos);
        model.addAttribute("correctAnswers", correctAnswers);
        return "quiz/evaluate";
    }

}


