package io.github.fixitlater.quizapp.challenge;

import io.github.fixitlater.quizapp.dtos.UserDto;
import io.github.fixitlater.quizapp.entities.Role;
import io.github.fixitlater.quizapp.entities.User;
import io.github.fixitlater.quizapp.forms.RegistrationForm;
import io.github.fixitlater.quizapp.repositories.RoleRepository;
import io.github.fixitlater.quizapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ChallengesUserService {

    private static final String ROLE_USER = "ROLE_USER";

    private ChallengeUserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private ChallengeUserContextService userContextService;

    @Autowired
    public ChallengesUserService(ChallengeUserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ChallengeUserContextService userContextService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userContextService = userContextService;
    }


    private boolean checkIfUserExist(String email) {
        Optional<User> userOptional = userRepository.findAllByEmail(email);
        return userOptional.isPresent();
    }


}