package com.blog.app.controller;

import com.blog.app.model.CommentDTO;
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

    /*
    * Instead of returning a DTO, adding these annotations to 'Comment' on the 'post' field could also be an option
    *       @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    *       @JsonIdentityReference(alwaysAsId = true)
    *       @JsonProperty(value = "post_id")
    * */
    @GetMapping("/{postId}")
    public List<CommentDTO> findByPostId(@PathVariable Long postId) {
        return commentService.findByPostId(postId);
    }
}
