package com.blog.app.service;

import com.blog.app.exception.NotFoundException;
import com.blog.app.model.Comment;
import com.blog.app.model.CommentDTO;
import com.blog.app.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostService postService;

    public List<Comment> save(List<Comment> comments) {
        return commentRepository.saveAll(comments);
    }

    public Comment save(Comment c) {
        return commentRepository.save(c);
    }

    public List<CommentDTO> findByPostId(Long postId) {
        List<Comment> comments = commentRepository.findAllByPostId(postId);
        if (comments == null || comments.isEmpty())
            throw new NotFoundException(String.format("Post with id '%d' not found or it has no comments", postId));
        return comments.stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }
}
