package io.github.fixitlater.quizapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class AnswerRecord {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean isCorrect;
    @Column (name = "category")
    private String category;
    @Column (name = "language")
    private String language;
    @ManyToOne
    private QuizAttempt quizAttempt;
}
