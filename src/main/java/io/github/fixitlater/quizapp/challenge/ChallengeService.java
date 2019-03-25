package io.github.fixitlater.quizapp.challenge;

import io.github.fixitlater.quizapp.entities.RoleType;
import io.github.fixitlater.quizapp.entities.User;
import io.github.fixitlater.quizapp.repositories.UserRepository;
import io.github.fixitlater.quizapp.services.UserContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChallengeService {

    private UserRepository userRepository;
    private UserContextService userContextService;

    @Autowired
    public ChallengeService(UserRepository userRepository, UserContextService userContextService) {
        this.userRepository = userRepository;
        this.userContextService = userContextService;
    }

    public List<User> getListOfAllUsersNotAdmins(){
        return userRepository.findAllByRoleRoleName(RoleType.ROLE_USER);
    }


}
