package io.github.fixitlater.quizapp.challenge;

import io.github.fixitlater.quizapp.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/challenge")
public class ChallengeController {

    private ChallengeUserContextService userContextService;
    private ChallengeService challengeService;

    @Autowired
    public ChallengeController(ChallengeUserContextService userContextService, ChallengeService challengeService) {
        this.userContextService = userContextService;
        this.challengeService = challengeService;
    }


    @GetMapping("/")
    public String challengeMainPage(Model model){
        if (!userContextService.isLogged()) return "redirect:/user/login";
        User loggedUser = userContextService.getLoggedUser();
        if (loggedUser.getDisplayName() == null) {
            model.addAttribute("loggedUser");
            return "challange/setDisplayName";
        }
        model.addAttribute("displayName", loggedUser.getDisplayName());
        return "/challenge/challengeMenu";
    }

    @GetMapping("/findOpponent")
    public String showFindOpponentPage(Model model){

        return "/challenge/findOpponent";
    }
}
