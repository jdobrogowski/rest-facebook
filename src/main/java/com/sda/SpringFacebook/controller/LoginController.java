package com.sda.SpringFacebook.controller;

import com.sda.SpringFacebook.request.UserLoginRequest;
import com.sda.SpringFacebook.services.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

    private UserLoginService userLoginService;

    @Autowired
    public LoginController(UserLoginService userLoginService){
        this.userLoginService = userLoginService;
    }

    @PostMapping("/login")
    public void loginUser(@RequestBody @Valid UserLoginRequest request){
        userLoginService.loginUser(request);
    }

}
