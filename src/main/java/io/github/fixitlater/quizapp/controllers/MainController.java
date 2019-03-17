package io.github.fixitlater.quizapp.controllers;

import io.github.fixitlater.quizapp.entities.QuestionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {

    private RestTemplate restTemplate;

    @Autowired
    public MainController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/")
    public String homePage() {
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
        QuestionDto[] questionDtos = restTemplate.getForObject("http://fix-it-later-quiz-api.herokuapp.com/questions/all", QuestionDto[].class);

        List<QuestionDto> questions = Arrays.asList(questionDtos);
        model.addAttribute("questions", questions);
        return "quiz/allQuestions";
    }

}
