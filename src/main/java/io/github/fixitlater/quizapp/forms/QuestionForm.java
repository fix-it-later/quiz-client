package io.github.fixitlater.quizapp.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by ej on 19/03/2019.
 */
@Getter
@Setter
public class QuestionForm {
    @NotNull(message = "field can't be empty")
    private String formQuestion;
    @NotBlank(message = "please choose")
    private String formCategory; //czy moze byc Category?
    @NotBlank(message = "please choose")
    private String formLanguage;
    @NotNull(message = "field can't be empty")
    private String formCorrectAnswer;

}
