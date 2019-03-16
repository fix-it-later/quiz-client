package io.github.fixitlater.quizapp.services;

import io.github.fixitlater.quizapp.entities.User;
import io.github.fixitlater.quizapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void saveUser (){
        User user = new User();
        user.setName("unknown");
        userRepository.save(user);
    }
}
