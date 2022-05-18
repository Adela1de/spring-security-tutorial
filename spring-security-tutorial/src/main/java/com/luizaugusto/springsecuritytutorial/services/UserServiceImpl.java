package com.luizaugusto.springsecuritytutorial.services;

import com.luizaugusto.springsecuritytutorial.entitites.User;
import com.luizaugusto.springsecuritytutorial.entitites.VerificationToken;
import com.luizaugusto.springsecuritytutorial.model.UserModel;
import com.luizaugusto.springsecuritytutorial.repositories.UserRepository;
import com.luizaugusto.springsecuritytutorial.repositories.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;

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

    @Override
    public void saveVerificationTokenForUser(User user, String token) {
         var verificationToken = new VerificationToken(user, token);
         verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        var verificationToken = verificationTokenRepository.findByToken(token);
        if(verificationToken == null) return "invalid";

        var user = verificationToken.getUser();
        var cal = Calendar.getInstance();

        if(verificationToken.getExpirationTime().getTime() - cal.getTime().getTime() <= 0)
        {
            verificationTokenRepository.delete(verificationToken);
            return "Expired!";
        }

        user.setEnabled(true);
        userRepository.save(user);

        return "valid";

    }

    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {

    }

    @Override
    public String validatePasswordResetToken(String token) {
        return null;
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.empty();
    }

    @Override
    public void changePassword(User user, String newPassword) {

    }

    @Override
    public boolean checkIfValidOldPassword(User user, String oldPassword) {
        return false;
    }
}
