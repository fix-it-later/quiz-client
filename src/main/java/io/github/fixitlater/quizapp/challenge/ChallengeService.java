package io.github.fixitlater.quizapp.challenge;

import io.github.fixitlater.quizapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChallengeService {

    private ChallengeUserRepository userRepository;
    private ChallengeUserContextService userContextService;

    @Autowired
    public ChallengeService(ChallengeUserRepository userRepository, ChallengeUserContextService userContextService) {
        this.userRepository = userRepository;
        this.userContextService = userContextService;
    }


    public List<DisplayName> getListOfAllOtherUsersDisplayNamees(){
        return userRepository.findAllByDisplayNameNotNull().stream().filter(u -> !u.equals(userContextService.getLoggedUser())).map(u -> u.getDisplayName()).collect(Collectors.toList());
    }

}
