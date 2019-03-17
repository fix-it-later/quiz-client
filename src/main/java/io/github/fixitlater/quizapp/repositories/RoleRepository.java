package io.github.fixitlater.quizapp.repositories;

import io.github.fixitlater.quizapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository <Role, Long> {
    Optional <Role> findByRoleName(String name);
}
