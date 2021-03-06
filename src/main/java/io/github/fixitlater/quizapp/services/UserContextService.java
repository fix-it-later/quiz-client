package io.github.fixitlater.quizapp.services;


import io.github.fixitlater.quizapp.entities.User;
import io.github.fixitlater.quizapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserContextService {

    private UserRepository userRepository;

    @Autowired
    public UserContextService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getLoggedAs() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return authentication.getName();
    }

    public boolean isLogged() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    public boolean hasRoleAdmin() {
        String username = getLoggedAs();
        Optional<User> userOptional = userRepository.findAllByEmail(username);
        if(userOptional.isPresent()){
            String role = userOptional.get().getRole().getRoleName();
            if (role.equals("ROLE_ADMIN")) {
                return true;
            }
        }
        return false;
    }
}
