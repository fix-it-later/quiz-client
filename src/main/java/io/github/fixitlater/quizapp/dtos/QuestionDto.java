package io.github.fixitlater.quizapp.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.fixitlater.quizapp.entities.Category;
import io.github.fixitlater.quizapp.entities.Language;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    public List<AnswerDto> getAnswers() {
        Collections.shuffle(answers);
        return answers;
    }

    private List<AnswerDto> answers;
}
