package io.github.fixitlater.quizapp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long questionId;
    private String questionBody;
    private String category;
    private String language;

    private List<AnswerDto> answers;

    public List<AnswerDto> shuffleAndGet() {
        Collections.shuffle(answers);
        return answers;
    }
}
