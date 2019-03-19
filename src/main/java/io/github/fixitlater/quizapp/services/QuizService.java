package io.github.fixitlater.quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {
    private RestTemplate restTemplate;

    @Autowired
    public QuizService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<String> getCategoryList(){
        String [] arr = restTemplate.getForObject("http://fix-it-later-quiz-api.herokuapp.com/categories", String[].class );
        return Arrays.asList(arr);
    }
    public List<String> getLanguageList(){
        String [] arr = restTemplate.getForObject("http://fix-it-later-quiz-api.herokuapp.com/languages", String[].class );
        return Arrays.asList(arr);
    }
}
