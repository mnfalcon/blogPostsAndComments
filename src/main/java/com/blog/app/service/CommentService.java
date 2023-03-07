package com.blog.app.service;

import com.blog.app.model.Comment;
import com.blog.app.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void save(Comment[] comments) {
        commentRepository.saveAll(List.of(comments));
    }

    public Comment save(Comment c) {
        return commentRepository.save(c);
    }
}
