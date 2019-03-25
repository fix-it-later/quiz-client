package io.github.fixitlater.quizapp.services;

import com.google.gson.Gson;
import io.github.fixitlater.quizapp.dtos.AnswerDto;
import io.github.fixitlater.quizapp.dtos.QuestionDto;
import io.github.fixitlater.quizapp.forms.QuestionForm;
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

    @Value("${API_URL}")
    private String apiUrl;

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
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-userKey", quizApikey);
        HttpEntity<QuestionDto> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl + "/questions/all",
                HttpMethod.GET, request, String.class);
        String body = response.getBody();
        return responseToDtoArray(body);
    }

    public QuestionDto getRandomQuestion() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-userKey", quizApikey);
        HttpEntity<QuestionDto> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(apiUrl + "/questions/random",
                HttpMethod.GET, request, String.class);
        String body = response.getBody();
        return responseToDto(body);
    }

    public boolean saveQuestion(QuestionForm questionForm){
        QuestionDto questionDto = questionFormToDto(questionForm);

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-userKey", quizApikey);
        HttpEntity<QuestionDto> request = new HttpEntity<>(questionDto, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl + "/questions/add", request, String.class);

        return response.getStatusCode().equals(HttpStatus.CREATED);
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

    private QuestionDto[] responseToDtoArray(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, QuestionDto[].class);
    }

    private QuestionDto responseToDto(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, QuestionDto.class);
    }
}
