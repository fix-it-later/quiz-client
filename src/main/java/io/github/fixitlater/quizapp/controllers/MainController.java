package io.github.fixitlater.quizapp.controllers;

import io.github.fixitlater.quizapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {

    private UserService userService;
    @Autowired
    public MainController (UserService userService){
        this.userService = userService;
    }

    @GetMapping ("/")
    public String homePage(){
        return "index";
    }

}
