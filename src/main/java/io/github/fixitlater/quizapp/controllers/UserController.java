package io.github.fixitlater.quizapp.controllers;
import io.github.fixitlater.quizapp.dtos.UserDto;
import io.github.fixitlater.quizapp.forms.RegistrationForm;
import io.github.fixitlater.quizapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        return "user/userRegisterForm";
    }

    @PostMapping("/user/register/new")
    public String registerUser(@ModelAttribute @Valid RegistrationForm registrationForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRegisterForm", registrationForm);
            System.out.println("błędny formularz");
            return "user/userRegisterForm";
        } else {
            if(userService.saveUser(registrationForm)){
                System.out.println("new user");
                return "redirect:/index";
            }
            else {
                model.addAttribute("userRegisterForm", registrationForm);
                System.out.println("użytkownik istnieje");
                return "user/userRegisterForm";
            }
        }
    }

    @GetMapping("/user/login")
    public String goToLoginForm() {
        return "user/userLoginForm";
    }

    @GetMapping("/user/profile")
    public String goToUserProfile(Model model){
        UserDto userDto = userService.getUserDto();
        System.out.println(userDto.toString());
        model.addAttribute("userDto", userDto);
        return "user/userProfile";
    }

    @GetMapping("/user/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login";
    }
}
