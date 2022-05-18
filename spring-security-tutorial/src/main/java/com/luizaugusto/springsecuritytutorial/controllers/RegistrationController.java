package com.luizaugusto.springsecuritytutorial.controllers;

import com.luizaugusto.springsecuritytutorial.entitites.User;
import com.luizaugusto.springsecuritytutorial.entitites.VerificationToken;
import com.luizaugusto.springsecuritytutorial.event.RegistrationCompleteEvent;
import com.luizaugusto.springsecuritytutorial.model.UserModel;
import com.luizaugusto.springsecuritytutorial.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;

    private final ApplicationEventPublisher publisher;

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello world!";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request)
    {
        var user = userServiceImpl.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        return "success";
    }

    private String applicationUrl(HttpServletRequest request)
    {
        return "http://"+
                request.getServerName()+
                ":"+
                request.getServerPort()+
                request.getContextPath();
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken,
                                          HttpServletRequest request)
    {
        var verificationToken = userServiceImpl.generateNewVerificationToken(oldToken);
        var user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "Verification Link Sent";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken token)
    {
        String url = applicationUrl + "/verifyRegistration?token="+token.getToken();
        log.info("Click the link to verify your account: {}", url);
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token)
    {
        var result = userServiceImpl.validateVerificationToken(token);
        if(result.equalsIgnoreCase("valid")) return "User verified successfully!";
        return "Bad User";
    }
}
