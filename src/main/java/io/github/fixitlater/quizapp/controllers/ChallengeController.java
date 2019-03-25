package io.github.fixitlater.quizapp.controllers;

import io.github.fixitlater.quizapp.entities.User;
import io.github.fixitlater.quizapp.challenge.ChallengeService;
import io.github.fixitlater.quizapp.services.UserContextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/challenge")
public class ChallengeController {

    private UserContextService userContextService;
    private ChallengeService challengeService;

    @Autowired
    public ChallengeController(UserContextService userContextService, ChallengeService challengeService) {
        this.userContextService = userContextService;
        this.challengeService = challengeService;
    }

    @GetMapping("/")
    public String challengeMainPage(Model model){
        User loggedUser = userContextService.getLoggedUser();
        if (loggedUser.getDisplayName() == null) return "challange/setDisplayName";
        model.addAttribute("displayName", loggedUser.getDisplayName());
        return "/challenge/challengeMenu";
    }

    @GetMapping("/findOpponent")
    public String showFindOpponentPage(Model model){

        return "/challenge/findOpponent";
    }

    @GetMapping("/list")
    public List<User> listUsers() {
        return challengeService.getListOfAllUsersNotAdmins();
    }
}
