package com.blog.app.controller;

import com.blog.app.model.Comment;
import com.blog.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{postId}")
    public List<Comment> findByPostId(@PathVariable Long postId) {
        return commentService.findByPostId(postId);
    }
}
