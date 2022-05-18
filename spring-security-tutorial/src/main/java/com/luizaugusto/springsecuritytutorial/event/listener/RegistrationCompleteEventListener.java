package com.luizaugusto.springsecuritytutorial.event.listener;

import com.luizaugusto.springsecuritytutorial.event.RegistrationCompleteEvent;
import com.luizaugusto.springsecuritytutorial.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

import java.util.UUID;
@RequiredArgsConstructor
@Slf4j
public class RegistrationCompleteEventListener implements
        ApplicationListener<RegistrationCompleteEvent>
{

    private final UserServiceImpl userServiceImpl;
    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event)
    {
        var user = event.getUser();
        var token = UUID.randomUUID().toString();
        userServiceImpl.saveVerificationTokenForUser(user, token);

        String url = event.getApplicationUrl() + "verifyRegistration?token=" + token;
        log.info("Click the link to verify your account: {}", url);
    }
}
