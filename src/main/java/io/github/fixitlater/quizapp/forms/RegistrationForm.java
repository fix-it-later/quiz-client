package io.github.fixitlater.quizapp.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegistrationForm {
    @NotBlank (message = "field can't be empty")
    private String name;
    @Email (message = "email example: example@gmail.com")
    @NotBlank
    private String email;
    @NotNull (message = "field can't be empty")
    private String password;
}
