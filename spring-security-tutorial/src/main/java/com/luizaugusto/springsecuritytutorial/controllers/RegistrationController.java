package com.luizaugusto.springsecuritytutorial.controllers;

import com.luizaugusto.springsecuritytutorial.model.UserModel;
import com.luizaugusto.springsecuritytutorial.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel)
    {
        var user = userService.registerUser(userModel);
        return null;

    }
}
