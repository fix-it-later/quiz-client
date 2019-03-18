package io.github.fixitlater.quizapp.repositories;

import io.github.fixitlater.quizapp.entities.QuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizAttemptRepository extends JpaRepository <QuizAttempt, Long> {
}
