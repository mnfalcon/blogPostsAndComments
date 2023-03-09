package com.blog.app.service;

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

    public List<Comment> save(List<Comment> comments) {
        return commentRepository.saveAll(comments);
    }

    public Comment save(Comment c) {
        return commentRepository.save(c);
    }

    public List<CommentDTO> findByPostId(Long postId) {
        return commentRepository.findAllByPostId(postId).stream()
                .map(CommentDTO::new)
                .collect(Collectors.toList());
    }
}
