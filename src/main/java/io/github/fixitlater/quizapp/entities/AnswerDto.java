package io.github.fixitlater.quizapp.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ej on 17/03/2019.
 */
@Getter
@Setter
@ToString
public class AnswerDto {
    private int answerNo;
    private String answerBody;
    private boolean isCorrect;
}
