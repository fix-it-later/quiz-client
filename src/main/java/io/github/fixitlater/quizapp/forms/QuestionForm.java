package io.github.fixitlater.quizapp.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by ej on 19/03/2019.
 */
@Getter
@Setter
public class QuestionForm {
    @NotBlank(message = "field can't be empty")
    private String formQuestion;
    @NotBlank(message = "please choose")
    private String formCategory; //czy moze byc Category?
    @NotBlank(message = "please choose")
    private String formLanguage;
    @NotBlank(message = "field can't be empty")
    private String formCorrectAnswer;
    @Size(min = 1, message = "at least one incorrect answer required")
    private String[] formIncorrectAnswers;
}
