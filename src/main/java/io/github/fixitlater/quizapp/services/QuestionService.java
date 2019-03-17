package io.github.fixitlater.quizapp.services;

import io.github.fixitlater.quizapp.dtos.AnswerDto;
import io.github.fixitlater.quizapp.dtos.QuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by ej on 17/03/2019.
 */
@Service
public class QuestionService {

    public int evaluateAnswers(Map<String, String> allParameters, QuestionDto[] questionDtos){
        int counter = 0;
        int correctAnswer = 0;
        for(QuestionDto q : questionDtos){
            List<AnswerDto> answers = q.getAnswers();
            for(AnswerDto a : answers){
                if(a.isCorrect()){
                    correctAnswer = a.getAnswerNo();
                }
            }
            if(Integer.parseInt(allParameters.get(q.getQuestionId().toString())) == correctAnswer){
                counter++;
            }
        }
        return counter;
    }
}
