package com.sda.SpringFacebook.database;

import com.sda.SpringFacebook.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserRepository extends MongoRepository<User, String> {

    Page<User> findAll(Pageable pageable);

    User findById(String id);

    Page<User> findAllByLoginIgnoreCaseLikeOrFirstNameIgnoreCaseLikeOrLastNameIgnoreCaseLike(String phraseLogin,
                                                                                             String phraseFirstName,
                                                                                             String phraseLastName,
                                                                                             Pageable pageable);
    User findUsersByLogin(String login);

}
