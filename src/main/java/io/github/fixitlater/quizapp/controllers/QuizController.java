package io.github.fixitlater.quizapp.controllers;

import io.github.fixitlater.quizapp.dtos.QuestionDto;
import io.github.fixitlater.quizapp.forms.QuestionForm;
import io.github.fixitlater.quizapp.services.QuestionService;
import io.github.fixitlater.quizapp.services.QuizService;
import io.github.fixitlater.quizapp.services.QuizStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String showParamQuiz(@RequestParam(required = false) String category, @RequestParam(required = false) String lang) {
        System.out.println(category + " " + lang);
        return "index";
    }

    @PostMapping("/quiz/quizAttempt")
    public String attemptQuiz(@RequestParam(required = false) String category,
                              @RequestParam(required = false) String language, Model model) {
        QuestionDto[] questionDtos = questionService.getQuestions(category, language);
        String uid = quizStorage.addToQuizMap(questionDtos);
        model.addAttribute("questions", Arrays.asList(questionDtos));
        model.addAttribute("uid", uid);
        return "quiz/quizAttempt";
    }

    @GetMapping("/quiz/random")
    public String showRandomQuestion(Model model) {
        QuestionDto questionDto = questionService.getRandomQuestion();
        QuestionDto[] questionDtos = {questionDto};
        model.addAttribute("question", questionDto);
        String uid = quizStorage.addToQuizMap(questionDtos);
        model.addAttribute("uid", uid);
        return "quiz/randomQuestion";
    }

    @GetMapping("/quiz/questions")
    public String showQuestions(Model model) {
        QuestionDto[] questionDtos = questionService.getAllQuestions();
        String uid = quizStorage.addToQuizMap(questionDtos);
        model.addAttribute("questions", Arrays.asList(questionDtos));
        model.addAttribute("uid", uid);
        return "quiz/allQuestions";
    }

    @PostMapping("/quiz/results/{uid}")
    public String evaluateAnswers(@RequestParam Map<String, String> allParameters, @PathVariable String uid, Model model) {
        QuestionDto[] questionDtos = quizStorage.getQuiz(uid);
        int correctAnswers = questionService.evaluateAnswers(allParameters, questionDtos);
        model.addAttribute("correctAnswers", correctAnswers);
        return "quiz/evaluate";
    }

    @GetMapping("/admin/create")
    public String questionForm(Model model) {
        model.addAttribute("questionForm", new QuestionForm());
        model.addAttribute("categories", quizService.getCategoryList());
        model.addAttribute("languages", quizService.getLanguageList());
        return "admin/createQuestionForm";
    }

    @PostMapping("/admin/create")
    public String createQuestion(@ModelAttribute @Valid QuestionForm questionForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("questionForm", questionForm);
            model.addAttribute("categories", quizService.getCategoryList());
            model.addAttribute("languages", quizService.getLanguageList());
            return "admin/createQuestionForm";
        }
        if (questionService.saveQuestion(questionForm)) {
            return "success";
        }
        return "failure";
    }

}
