package com.sda.SpringFacebook.services;

import com.sda.SpringFacebook.database.PostRepository;
import com.sda.SpringFacebook.database.UserRepository;
import com.sda.SpringFacebook.enums.RangeOfPost;
import com.sda.SpringFacebook.exceptions.NoAccessToThisOperationException;
import com.sda.SpringFacebook.exceptions.PostNotExistException;
import com.sda.SpringFacebook.exceptions.UserNotExistException;
import com.sda.SpringFacebook.model.Post;
import com.sda.SpringFacebook.model.User;
import com.sda.SpringFacebook.request.CreatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl {

    private UserRepository userRepository;
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public void createPost(CreatePostRequest request) {

        User user = getUserLoggedInFromRepository();

        Post post = Post.builder()
                .postContent(request.getPostContent())
                .userId(user.getId())
                .date(LocalDate.now())
                .time(LocalTime.now())
                .rangeOfPost(request.getRangeOfPost())
                .like(new HashSet<>())
                .build();

        postRepository.save(post);
    }

    public Page<Post> viewPostsYouAreTheAuthor(Pageable pageable) {

        User user = getUserLoggedInFromRepository();

        return postRepository.findAllByUserId(user.getId(), pageable);
    }

    public List<Post> viewAllPublicPostAndAllPostFriends() {

        return takeAllPostsAvailableForUser();
    }

    public void deletePost(String id) {

        //User user = getUserLoggedInFromRepository();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        User user = userRepository.findUsersByLogin(name);

        Post byId = postRepository.findById(id);
        if (byId == null) {

            throw new PostNotExistException("Post nie istnieje");
        }
        if (!user.getId().equals(byId.getUserId())) {
            throw new NoAccessToThisOperationException("Nie masz uprwnień");
        }
        postRepository.delete(id);
    }

    public void addLike(String postId, String id) {

        Post post = checkIfPostExist(postId);
        post.getLike().add(id);
        postRepository.save(post);
    }

    public void editPost(String id, String content) {

        Post post = checkIfPostExist(id);
        post.setPostContent(content);
        postRepository.save(post);
    }

    private Post checkIfPostExist(String id) {

        Post postById = postRepository.findById(id);
        if (postById == null) {

            throw new PostNotExistException("Post o podanym id nie istnieje");
        }
        return postById;
    }

    private User getUserLoggedInFromRepository() {

        return userRepository.findAll().stream()
                .filter(u -> u.getLogin().equalsIgnoreCase(UserContextHolder.getUserLoggedIn()))
                .findFirst()
                .orElseThrow(() -> new UserNotExistException("Użytkownik " + UserContextHolder.getUserLoggedIn() + " nie istnieje"));
    }


    private List<Post> takeAllPostsAvailableForUser() {

        User user = getUserLoggedInFromRepository();

        List<Post> publicPosts = postRepository.findAllByRangeOfPost(RangeOfPost.PUBLIC);

        Map<String, Post> usersPrivatePosts = postRepository.findAllByRangeOfPost(RangeOfPost.PRIVATE)
                .stream()
                .collect(Collectors.toMap(Post::getUserId, p -> p));

        List<Post> collect = user.getFriends()
                .stream()
                .filter(usersPrivatePosts::containsKey)
                .map(usersPrivatePosts::get)
                .collect(Collectors.toList());

        collect.addAll(publicPosts);
        return collect;
    }

}
