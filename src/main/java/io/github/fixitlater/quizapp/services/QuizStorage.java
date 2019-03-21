package io.github.fixitlater.quizapp.services;

import io.github.fixitlater.quizapp.dtos.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class QuizStorage {

    private Map<String, QuestionDto[]> quizMap;

    public String addToQuizMap(QuestionDto[] questionDtos) {
        if (quizMap == null) {
            quizMap = new HashMap<>();
        }
        String uid = UUID.randomUUID().toString();
        quizMap.put(uid, questionDtos);
        return uid;
    }

    public QuestionDto[] getQuiz(String uid) {
        return quizMap.get(uid);
    }

    public void deleteQuestionDtos(String key) {
        quizMap.remove(key);
    }
}
