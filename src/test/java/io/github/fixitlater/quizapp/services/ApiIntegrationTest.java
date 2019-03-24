package io.github.fixitlater.quizapp.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.fixitlater.quizapp.dtos.AnswerDto;
import io.github.fixitlater.quizapp.dtos.QuestionDto;
import io.github.fixitlater.quizapp.entities.Category;
import io.github.fixitlater.quizapp.entities.Language;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

/**
 * Created by ej on 24/03/2019.
 */
public class ApiIntegrationTest {

    @Value("${quiz.apikey}")
    private String quizApikey;

    public static final ArrayList<AnswerDto> ANSWERS = new ArrayList<>();

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    public void apiTest() {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setCategory(Category.ENGLISH.name());
        questionDto.setLanguage(Language.POLISH.name());
        questionDto.setQuestionBody("1 + 1 ?");
        AnswerDto a = new AnswerDto();
        a.setCorrect(true);
        a.setAnswerBody("2");

        AnswerDto b = new AnswerDto();
        b.setCorrect(false);
        b.setAnswerBody("6");

        ANSWERS.add(a);
        ANSWERS.add(b);
        questionDto.setAnswers(ANSWERS);
        ObjectMapper mapper = new ObjectMapper();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-userKey", quizApikey);
        HttpEntity<QuestionDto> request = new HttpEntity<>(questionDto, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8089/questions/add", request, String.class);
    }
}
