package io.github.fixitlater.quizapp.challenge;

import io.github.fixitlater.quizapp.entities.Role;
import io.github.fixitlater.quizapp.entities.User;
import io.github.fixitlater.quizapp.repositories.UserRepository;
import io.github.fixitlater.quizapp.services.UserContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ChallengeUserContextService {

    private ChallengeUserRepository userRepository;

    @Autowired
    public ChallengeUserContextService(ChallengeUserRepository userRepository) {
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

    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }
        return userRepository.findOneByEmail(authentication.getName()).orElse(null);
    }

}
