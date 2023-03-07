package com.blog.app.service;

import com.blog.app.model.Post;
import com.blog.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public boolean postExists(Post post) {
        return postRepository.findById(post.getId()).isPresent();
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> save(ArrayList<Post> posts) {
        return postRepository.saveAll(posts);
    }
}
