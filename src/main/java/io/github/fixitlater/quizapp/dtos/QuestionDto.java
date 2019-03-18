package io.github.fixitlater.quizapp.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

/**
 * Created by ej on 17/03/2019.
 */
@Getter
@Setter
@ToString
public class QuestionDto {

    private Long questionId;
    private String questionBody;

    public List<AnswerDto> getAnswers() {
        Collections.shuffle(answers);
        return answers;
    }

    private List<AnswerDto> answers;
}
