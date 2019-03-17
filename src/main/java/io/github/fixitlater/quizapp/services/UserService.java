package io.github.fixitlater.quizapp.services;

import io.github.fixitlater.quizapp.entities.Role;
import io.github.fixitlater.quizapp.entities.User;
import io.github.fixitlater.quizapp.forms.RegistrationForm;
import io.github.fixitlater.quizapp.repositories.RoleRepository;
import io.github.fixitlater.quizapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UserService {

    private static final String ROLE_USER = "ROLE_USER";

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean saveUser(RegistrationForm registrationForm) {
        if (checkIfUserExist(registrationForm.getEmail())) {
            return false;
        } else {
            User user = new User();
            user.setName(registrationForm.getFormName());
            user.setEmail(registrationForm.getEmail());
            user.setPasswordHash(passwordEncoder.encode(registrationForm.getPassword()));
            user.setAddedDate(LocalDate.now());
            getORCreateDefaultRole(user);
            userRepository.save(user);
            return true;
        }
    }

    private boolean checkIfUserExist(String email) {
        Optional<User> userOptional = userRepository.findAllByEmail(email);
        return userOptional.isPresent();
    }


    private void getORCreateDefaultRole(User user) {
        Role role = roleRepository.findByRoleName(ROLE_USER)
                .orElseGet(() -> roleRepository.save(new Role(ROLE_USER)));
        user.setRole(role);
    }
}