package com.sda.SpringFacebook.database;

import com.sda.SpringFacebook.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    Page<Post> findAllByUserId(String id, Pageable pageable);

    List<Post> findAllByRangeOfPost(Enum rangeOfPost);

    List<Post> findAll();

    Post findById(String id);
}
