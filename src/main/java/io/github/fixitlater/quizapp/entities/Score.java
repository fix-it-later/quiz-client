package io.github.fixitlater.quizapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

//@Entity
@Setter
@Getter
public class Score {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate approachDate;
    private Integer scoreAchieved;
    @Column (name = "category")
    private String category; //TODO
    @Column (name = "language")
    private String language;//TODO
    private User user;
}
