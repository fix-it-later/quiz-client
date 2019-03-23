package io.github.fixitlater.quizapp.services;

import io.github.fixitlater.quizapp.dtos.AnswerDto;
import io.github.fixitlater.quizapp.dtos.QuestionDto;
import io.github.fixitlater.quizapp.forms.QuestionForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by ej on 17/03/2019.
 */
@Service
public class QuestionService {

    private RestTemplate restTemplate;

    @Autowired
    public QuestionService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public int evaluateAnswers(Map<String, String> allParameters, QuestionDto[] questionDtos) {
        int counter = 0;
        int correctAnswer = 0;
        for (QuestionDto q : questionDtos) {
            List<AnswerDto> answers = q.getAnswers();
            for (AnswerDto a : answers) {
                if (a.isCorrect()) {
                    correctAnswer = a.getAnswerNo();
                }
            }
            if (allParameters.get(q.getQuestionId().toString()) != null &&
                    Integer.parseInt(allParameters.get(q.getQuestionId().toString())) == correctAnswer) {
                counter++;
            }
        }
        return counter;
    }

    public QuestionDto[] getAllQuestions() {
        QuestionDto[] questionDtos = restTemplate.getForObject("http://fix-it-later-quiz-api.herokuapp.com/questions/all", QuestionDto[].class);
        return questionDtos;
    }

    public QuestionDto getRandomQuestion() {
        QuestionDto questionDto = restTemplate.getForObject("http://fix-it-later-quiz-api.herokuapp.com/questions/random", QuestionDto.class);
        return questionDto;
    }

    public boolean saveQuestion(QuestionForm questionForm) { //TODO

        return true;
    }
}
