package io.github.fixitlater.quizapp.repositories;

import io.github.fixitlater.quizapp.entities.AnswerRecord;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRecordRepository extends JpaRepository<AnswerRecord, Long> {
}
