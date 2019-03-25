package io.github.fixitlater.quizapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class QuizService {
    private RestTemplate restTemplate;
    @Value("${quiz.apikey}")
    private String quizApikey;
    @Value("${API_URL}")
    private String apiUrl;

    @Autowired
    public QuizService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplate = restTemplateBuilder.build();
    }

    public List<String> getCategoryList(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-userKey", quizApikey);
        HttpEntity<String[]> request = new HttpEntity<>(headers);
        ResponseEntity<String[]> response = restTemplate.exchange(apiUrl + "/categories",
                HttpMethod.GET, request, String[].class);
        return Arrays.asList(response.getBody());
    }

    public List<String> getLanguageList(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-userKey", quizApikey);
        HttpEntity<String[]> request = new HttpEntity<>(headers);
        ResponseEntity<String[]> response = restTemplate.exchange(apiUrl + "/languages",
                HttpMethod.GET, request, String[].class);
        return Arrays.asList(response.getBody());
    }

}
