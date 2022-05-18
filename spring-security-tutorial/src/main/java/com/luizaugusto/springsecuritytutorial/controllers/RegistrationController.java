package com.luizaugusto.springsecuritytutorial.controllers;

import com.luizaugusto.springsecuritytutorial.model.UserModel;
import com.luizaugusto.springsecuritytutorial.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@CrossOrigin("*")
public class RegistrationController {

    private final UserService userService;


    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel)
    {
        var user = userService.registerUser(userModel);
        return "success";
    }
}
