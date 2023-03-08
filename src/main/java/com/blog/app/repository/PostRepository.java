package com.blog.app.repository;


import com.blog.app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
//    @Query("SELECT p FROM Post p WHERE p.title LIKE %:param%")
    List<Post> findByTitleLike(String param);
}
