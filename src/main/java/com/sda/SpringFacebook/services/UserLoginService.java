package com.sda.SpringFacebook.services;

import com.sda.SpringFacebook.database.UserRepository;
import com.sda.SpringFacebook.exceptions.PasswordNotCorrectException;
import com.sda.SpringFacebook.exceptions.UserNotExistException;
import com.sda.SpringFacebook.model.User;
import com.sda.SpringFacebook.request.UserLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {

    private UserRepository userRepository;

    @Autowired
    public UserLoginService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void loginUser(UserLoginRequest request) {

        User user = userRepository.findAll()
                .stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(request.getLogin().trim()))
                .findFirst()
                .orElseThrow(() -> new UserNotExistException("Użytkownik: " + request.getLogin() + " nie istnieje"));

        if (passwordIsNotCorrect(request, user)) {

            throw new PasswordNotCorrectException("Podane hasło jest nieprawidłowe");
        }

        UserContextHolder.logInUser(user);
    }

    private boolean passwordIsNotCorrect(UserLoginRequest request, User user) {
        return !user.getLogin().equals(request.getLogin());
    }
}
