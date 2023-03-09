package com.blog.app.config;

import com.blog.app.model.Comment;
import com.blog.app.model.Post;
import com.blog.app.service.CommentService;
import com.blog.app.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class InitialSetup implements CommandLineRunner {

    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    private final RestTemplate restTemplate;
    private final String REMOTE_POSTS_URL;
    private final String REMOTE_COMMENTS_URL;

    public InitialSetup(@Value("${app.remote.posts.url}") String postsUrl, @Value("${app.remote.comments.url}") String commentsUrl) {
        this.restTemplate = new RestTemplate();
        this.REMOTE_POSTS_URL = postsUrl;
        this.REMOTE_COMMENTS_URL = commentsUrl;
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Fetching remote posts");
        Post[] remotePosts = {};
        try {
            remotePosts = restTemplate.getForObject(REMOTE_POSTS_URL, Post[].class);
            saveRemoteData(remotePosts);
        } catch (Exception e) {
            log.error("Fetching failed with exception: " + e.getMessage());
            log.info(REMOTE_POSTS_URL);
            e.printStackTrace();
        }
    }

    public void saveRemoteData(Post[] remotePosts) {

        if (remotePosts != null && remotePosts.length > 0) {
            ArrayList<Post> posts = new ArrayList<>();
            for (Post remotePost : remotePosts) {
                if (!postService.postExists(remotePost)) {
                    String commentsUrl = String.format(REMOTE_COMMENTS_URL, remotePost.getId());
                    Comment[] comments = restTemplate.getForObject(commentsUrl, Comment[].class);

                    if (comments != null && comments.length > 0) {
                        for (Comment c : comments) {
                            c.setPost(remotePost);
                        }
                        commentService.save(comments);
                        remotePost.setComments(List.of(comments));
                    }
                    posts.add(remotePost);
                }
            }
            postService.save(posts);
            log.info("Remote posts saved or already existed.");
        } else {
            log.info(String.format("Fetching from '%s' didn't return anything.", REMOTE_POSTS_URL));
        }
    }
}
