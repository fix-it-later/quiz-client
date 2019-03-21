package io.github.fixitlater.quizapp.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
public class QuizAttempt {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY )
    private Long id;
    @Column (name = "approach_date")
    private LocalDateTime approachDate;
    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;
    @OneToMany (mappedBy = "quizAttempt")
    private List<AnswerRecord> answerRecord;
}
