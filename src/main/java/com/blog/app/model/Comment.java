package com.blog.app.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "post_id")
    private Long postId;
    private String name;
    private String email;
    private String body;
}
