package com.sda.SpringFacebook.controller;

import com.sda.SpringFacebook.model.User;
import com.sda.SpringFacebook.request.CreateUserRequest;
import com.sda.SpringFacebook.request.UpdateUserRequest;
import com.sda.SpringFacebook.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController( UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPerson(@RequestBody @Valid CreateUserRequest request) {

        userService.createPerson(request);
    }

    @DeleteMapping("/user/{userId}/delete")
    public void deleteUser(@RequestParam String userId) {
        userService.deleteUser(userId);

    }

    @PutMapping("/user/{userId}/edit")
    public void createPerson(@RequestBody UpdateUserRequest request, @PathVariable String userId) {
        userService.changeUserDataById(request, userId);
    }

    @GetMapping("/user/show/all")
    public Page<User> findByLogin(@PageableDefault(size = 10) Pageable pageable) {
        return userService.getAllUsers(pageable);
    }

    @GetMapping("/users/search/{phrase}")
    public Page<User> findByPhrase(@PathVariable String phrase,
                                   @PageableDefault(size = 10) Pageable pageable) {
        return userService.getAllByPhrase(phrase, pageable);
    }


}
