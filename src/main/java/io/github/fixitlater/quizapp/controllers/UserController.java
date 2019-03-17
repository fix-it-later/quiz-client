package io.github.fixitlater.quizapp.controllers;

import io.github.fixitlater.quizapp.forms.RegistrationForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    @GetMapping("/user/register")
    public String goToRegisterForm(Model model){
        model.addAttribute("registrationForm", new RegistrationForm());
        return "/user/userRegisterForm";
    }



    @GetMapping("/user/login")
    public String goToLoginForm(){
        return "/user/userLoginForm";
    }
    @PostMapping("/user/register/new")
    public String registerUser(@ModelAttribute @Valid RegistrationForm registrationForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/user/userRegisterForm";
        }else
        System.out.println("new user");
        return "redirect:/index";
    }
}
