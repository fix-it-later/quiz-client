package io.github.fixitlater.quizapp.controllers;

import io.github.fixitlater.quizapp.forms.RegistrationForm;
import io.github.fixitlater.quizapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController (UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/register")
    public String goToRegisterForm(Model model) {
        model.addAttribute("userRegisterForm", new RegistrationForm());
        return "/user/userRegisterForm";
    }

    @PostMapping("/user/register/new")
    public String registerUser(@ModelAttribute @Valid RegistrationForm registrationForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegisterForm", registrationForm);
            System.out.println("błędny formularz");
            return "/user/userRegisterForm";
        } else {
            if(userService.saveUser(registrationForm)){
                System.out.println("new user");
                return "redirect:/index";
            }
            else {
                model.addAttribute("userRegisterForm", registrationForm);
                System.out.println("użytkownik istnieje");
                return "/user/userRegisterForm";
            }
        }
    }

    @GetMapping("/user/login")
    public String goToLoginForm() {
        return "/user/userLoginForm";
    }

}
