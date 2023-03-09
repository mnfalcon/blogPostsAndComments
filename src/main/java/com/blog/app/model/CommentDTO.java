package com.blog.app.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

    public CommentDTO(Comment c) {
        this.id = c.getId();
        this.postId = c.getPost().getId();
        this.name = c.getName();
        this.email = c.getEmail();
        this.body = c.getBody();
    }
}
