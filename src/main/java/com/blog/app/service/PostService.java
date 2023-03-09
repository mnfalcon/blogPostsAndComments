package com.blog.app.service;

import com.blog.app.exception.NotFoundException;
import com.blog.app.model.Post;
import com.blog.app.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public boolean postExists(Long postId) {
        return postRepository.existsById(postId);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public List<Post> save(List<Post> posts) {
        return postRepository.saveAll(posts);
    }

    public Page<Post> getPostsByPage(int page, int limit) {
        PageRequest r = PageRequest.of(page, limit, Sort.by("title"));
        return postRepository.findAll(r);
    }

    public Post findById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Post with id '%d' not found.", id)));
    }

    public List<Post> searchPosts(String param) {
        return postRepository.findByTitleContaining(param);
    }
}
