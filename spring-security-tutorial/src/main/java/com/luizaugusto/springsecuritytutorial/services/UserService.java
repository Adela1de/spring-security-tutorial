package com.luizaugusto.springsecuritytutorial.services;

import com.luizaugusto.springsecuritytutorial.entitites.User;
import com.luizaugusto.springsecuritytutorial.model.UserModel;
import com.luizaugusto.springsecuritytutorial.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserModel userModel)
    {
        var user = new User();
        user.setEmail(userModel.getEmail());
        user.setFirstName(userModel.getFirstName());
        user.setLastName(userModel.getLastName());
        user.setPassword(passwordEncoder.encode(userModel.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return user;
    }
}
