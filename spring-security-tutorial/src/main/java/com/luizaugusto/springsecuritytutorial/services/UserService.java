package com.luizaugusto.springsecuritytutorial.services;

import com.luizaugusto.springsecuritytutorial.entitites.User;
import com.luizaugusto.springsecuritytutorial.entitites.VerificationToken;
import com.luizaugusto.springsecuritytutorial.model.UserModel;

import java.util.Optional;

public interface UserService {
    User registerUser(UserModel userModel);

    void saveVerificationTokenForUser(User user, String token);

    String validateVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean checkIfValidOldPassword(User user, String oldPassword);
}
