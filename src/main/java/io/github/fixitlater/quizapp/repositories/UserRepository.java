package io.github.fixitlater.quizapp.repositories;

import io.github.fixitlater.quizapp.entities.RoleType;
import io.github.fixitlater.quizapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findAllByEmail (String email);

    List<User> findAllByRoleRoleName(RoleType type);

    Optional<User> findOneByEmail(String email);


}
