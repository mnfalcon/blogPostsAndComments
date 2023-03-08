package com.blog.app.controller;

import com.blog.app.model.Post;
import com.blog.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public Page<Post> getPostsByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                     @RequestParam(name = "limit", defaultValue = "20") Integer limit) {
        return postService.getPostsByPage(page, limit);
    }

    @GetMapping("/{id}")
    public Post findPost(@PathVariable Long id) {
        return postService.findById(id);
    }

    /*
    * The search parameter could be included in the getPostsByPage method with the @RequestParam
    * annotation.
    * */
    @GetMapping("/search/{param}")
    public List<Post> searchPosts(@PathVariable String param) {
        return postService.searchPosts(param);
    }

}
