package io.github.fixitlater.quizapp.challenge;

import io.github.fixitlater.quizapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChallengeUserRepository extends JpaRepository<User, Long> {
    Optional <User> findAllByEmail(String email);

    Optional<User> findOneByEmail(String email);

    List<User> findAllByDisplayNameNotNull();

}
