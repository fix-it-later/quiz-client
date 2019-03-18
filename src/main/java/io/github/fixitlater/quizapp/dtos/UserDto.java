package io.github.fixitlater.quizapp.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;


@ToString
@Getter
@Setter
@NoArgsConstructor
@Component
public class UserDto {
    private String nameDto;
    private String emailDto;
}
