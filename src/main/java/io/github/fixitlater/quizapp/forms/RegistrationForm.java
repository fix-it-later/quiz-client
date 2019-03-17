package io.github.fixitlater.quizapp.forms;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RegistrationForm {
    @NotNull(message = "field can't be empty")
    private String formName;
    @Email (message = "email example: example@gmail.com")
    private String email;
    @Size (min = 4, max = 20, message = "password should contain at least 4 characters")
    private String password;
}
