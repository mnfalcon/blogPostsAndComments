package com.blog.app.service;

import com.blog.app.exception.NotFoundException;
import com.blog.app.model.Post;
import com.blog.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<Post> getPostsByPage(int page, int limit) {
        PageRequest r = PageRequest.of(page, limit, Sort.by("title"));
        return postRepository.findAll(r);
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new NotFoundException("Post with id '%d' not found."));
    }

    public List<Post> searchPosts(String param) {
        return postRepository.findByTitleLike("%" + param + "%");
    }
}
