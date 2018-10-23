package com.sda.SpringFacebook.services;

import com.sda.SpringFacebook.model.User;
import com.sda.SpringFacebook.model.UserLoggedIn;

public class UserContextHolder {

    private static UserLoggedIn userLoggedIn;

    public static void logInUser(User user){
        userLoggedIn = new UserLoggedIn(user.getLogin());
    }

    public static String getUserLoggedIn(){
        return userLoggedIn == null ? null : userLoggedIn.getLogin();
    }

    public static void logOutUser(){
        if (userLoggedIn != null){
            userLoggedIn = null;
        }
    }
}
