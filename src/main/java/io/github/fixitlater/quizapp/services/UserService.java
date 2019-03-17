package io.github.fixitlater.quizapp.services;

import io.github.fixitlater.quizapp.entities.User;
import io.github.fixitlater.quizapp.forms.RegistrationForm;
import io.github.fixitlater.quizapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser (RegistrationForm registrationForm){
        User user = new User();
        user.setName(registrationForm.getName());
        user.setEmail(registrationForm.getEmail());
        user.setPasswordHash(registrationForm.getPassword());
        user.setAddedDate(LocalDate.now());
        userRepository.save(user);
    }

    private boolean checkIfUserExist(String name){
        return true;//TODO
    }

}
