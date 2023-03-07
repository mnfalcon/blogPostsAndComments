package com.blog.app.config;

import com.blog.app.model.Comment;
import com.blog.app.model.Post;
import com.blog.app.service.CommentService;
import com.blog.app.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class InitialSetup implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    private final RestTemplate restTemplate;
    private final String REMOTE_POSTS_URL = "https://jsonplaceholder.typicode.com/posts";
    private final String REMOTE_COMMENTS_URL = "https://jsonplaceholder.typicode.com/posts/%d/comments";

    public InitialSetup() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Fetching remote posts");
        Post[] remotePosts = restTemplate.getForObject(REMOTE_POSTS_URL, Post[].class);
        ArrayList<Post> posts = new ArrayList<>();
        for (int i = 0; i < remotePosts.length; i++) {
            if (!postService.postExists(remotePosts[i])) {
                String commentsUrl = String.format(REMOTE_COMMENTS_URL, remotePosts[i].getId());
                Comment[] comments = restTemplate.getForObject(commentsUrl, Comment[].class);


                if (comments != null && comments.length > 0) {
                    for (Comment c : comments) {
                        c.setPost(remotePosts[i]);
                    }
                    commentService.save(comments);
                    remotePosts[i].setComments(List.of(comments));
                }
                posts.add(remotePosts[i]);
//                System.out.println(remotePosts[i].getComments());
//                postService.save(remotePosts[i]);
            }
        }
        postService.save(posts);
        log.info("Remote posts saved or already existed.");

        Comment c = Comment.builder()
                .post(remotePosts[0])
                .body("hello")
                .email("atl@email.com")
                .name("Atl")
                .build();
        commentService.save(c);
    }
}
