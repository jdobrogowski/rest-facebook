package com.sda.SpringFacebook.services;

import com.sda.SpringFacebook.database.UserRepository;
import com.sda.SpringFacebook.exceptions.PhraseNotCorrectException;
import com.sda.SpringFacebook.model.User;
import com.sda.SpringFacebook.request.CreateUserRequest;
import com.sda.SpringFacebook.request.UpdateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    public void createPerson(CreateUserRequest request) {
        if (checkUserExists(request)) {
            System.err.println("Jest taki ziomek");
        } else {

            User person = User.builder()
                    .login(request.getLogin())
                    .firstName(request.getFirstName())
                    .lastName(request.getLastName())
                    .password(request.getPassword())
                    .friends(request.getFriends())
                    .build();

            repository.save(person);
        }

    }

    private boolean checkUserExists(CreateUserRequest request) {
        return repository.findAll().stream()
                .map(u -> u.getLogin())
                .anyMatch(e -> e.equals(request.getLogin()));
    }


    public void deleteUser(String userId) {

        //todo
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void changeUserDataById(UpdateUserRequest request, String userId) {
        User userToChange = repository.findById(userId);

        userToChange.setFirstName(request.getFirstName());
        userToChange.setLastName(request.getLastName());
        userToChange.setPassword(request.getPassword());

        repository.save(userToChange);
    }


    public Page<User> getAllByPhrase(String phrase, Pageable pageable) {
        Page<User> users = repository.findAllByLoginIgnoreCaseLikeOrFirstNameIgnoreCaseLikeOrLastNameIgnoreCaseLike(phrase, phrase, phrase, pageable);

        if (users == null || users.getContent().isEmpty()) {
            throw new PhraseNotCorrectException("Nie znaleziono żadnego użytkownika zawierającego \"" +
                    phrase + "\" w loginie, imieniu lub nazwisku");
        }

        return users;
    }

}
