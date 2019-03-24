package io.github.fixitlater.quizapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.fixitlater.quizapp.dtos.AnswerDto;
import io.github.fixitlater.quizapp.dtos.QuestionDto;
import io.github.fixitlater.quizapp.forms.QuestionForm;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ej on 17/03/2019.
 */
@Service
public class QuestionService {

    private RestTemplate restTemplate;

    @Value("${quiz.apikey}")
    private String quizApikey;

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

    public ResponseEntity saveQuestion(QuestionForm questionForm){
        QuestionDto questionDto = questionFormToDto(questionForm);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-userKey", quizApikey);
        HttpEntity<QuestionDto> request = new HttpEntity<>(questionDto, headers);
        ResponseEntity<String> response = restTemplate.postForEntity("http://fix-it-later-quiz-api.herokuapp.com/questions/add", request, String.class);

        return response;
    }

    private QuestionDto questionFormToDto(QuestionForm questionForm){
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionBody(questionForm.getFormQuestion());
        questionDto.setCategory(questionForm.getFormCategory());
        questionDto.setLanguage(questionForm.getFormLanguage());
        List<AnswerDto> answerList = new ArrayList<>();
        answerList.add(answerFormToDto(questionForm.getFormCorrectAnswer(), true));
        for(String incorrectAnswer : questionForm.getFormIncorrectAnswers()){
            answerList.add(answerFormToDto(incorrectAnswer, false));
        }
        questionDto.setAnswers(answerList);
        return questionDto;
    }

    private AnswerDto answerFormToDto(String formAnswer, boolean isCorrect){
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerBody(formAnswer);
        answerDto.setCorrect(isCorrect);
        return answerDto;
    }
}
