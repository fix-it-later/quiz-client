package io.github.fixitlater.quizapp.repositories;

import io.github.fixitlater.quizapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findAllByEmail (String email);
}
