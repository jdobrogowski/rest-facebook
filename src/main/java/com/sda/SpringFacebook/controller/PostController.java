package com.sda.SpringFacebook.controller;

import com.sda.SpringFacebook.model.Post;
import com.sda.SpringFacebook.request.CreatePostRequest;
import com.sda.SpringFacebook.services.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostController {

    private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }


    @PostMapping("/createPost")
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody @Valid CreatePostRequest request) {
        postService.createPost(request);
    }

    @GetMapping("/viewPostsYouAreTheAuthor")
    public Page<Post> viewPostsYouAreTheAuthor(@PageableDefault(value = 10)Pageable pageable) {

        return postService.viewPostsYouAreTheAuthor(pageable);
    }

    @GetMapping("/viewAllPosts")
    public List<Post> viewAllPosts() {

        return postService.viewAllPublicPostAndAllPostFriends();
    }

    @PostMapping("/user/{userId}/post/{postId}/addLike")
    public void addLike(@PathVariable String postId, @PathVariable String userId) {
        postService.addLike(postId, userId);
    }

    @PutMapping("/post/{postId}/editPost")
    public void editPost(@PathVariable String postId, @RequestBody String context) {

        postService.editPost(postId, context);
    }


    @DeleteMapping("/deletePost/{postId}")
    public void deletePost(@PathVariable String postId) {
        postService.deletePost(postId);
    }
}
