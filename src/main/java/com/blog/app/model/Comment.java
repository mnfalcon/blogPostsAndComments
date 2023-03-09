package com.blog.app.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Data
@Builder
@AllArgsConstructor
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private String name;
    private String email;

    @Column(length = 300)
    private String body;

    public Comment() {

    }
}
