package com.blog.app.service;

import com.blog.app.exception.NotFoundException;
import com.blog.app.model.Post;
import com.blog.app.repository.PostRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {
    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostService postService;

    private List<Post> posts = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        posts = new ArrayList<>();
        Post p1 = Post.builder()
                .id(1L)
                .body("earum voluptatem facere provident blanditiis velit laboriosam\\npariatur accusamus odio saepe")
                .title("id minus libero illum nam ad officiis")
                .userId(95L)
                .build();
        Post p2 = Post.builder()
                .id(2L)
                .body("in non odio excepturi sint eum\\nlabore voluptates vitae quia qui et\\ninventore itaque rerum\\nveniam non exercitationem delectus aut")
                .title("quaerat velit veniam amet cupiditate aut numquam ut sequi")
                .userId(96L)
                .build();
        posts.add(p1);
        posts.add(p2);
    }

    @Test
    public void testPostExists() {
        Post post = posts.get(0);
        Mockito.when(postRepository.existsById(post.getId()))
                .thenReturn(true);
        assertTrue(postService.postExists(post.getId()));
    }

    @Test
    public void testSave() {
        Post post = posts.get(0);
        Mockito.when(postRepository.save(post))
                .thenReturn(post);
        assertEquals(post, postService.save(post));
    }

    @Test
    public void testSaveAll() {
        Mockito.when(postRepository.saveAll(posts))
                .thenReturn(posts);
        assertEquals(posts, postService.save(posts));
    }

    @Test
    public void testGetPostsByPage() {
        int page = 0;
        int limit = 10;
        Page<Post> expectedPage = new PageImpl<>(posts);
        Pageable pageable = PageRequest.of(page, limit, Sort.by("title"));
        Mockito.when(postRepository.findAll(pageable))
                .thenReturn(expectedPage);
        assertEquals(expectedPage, postService.getPostsByPage(page, limit));

        limit = 1;
        expectedPage = new PageImpl<>(posts.subList(0, 1),
                PageRequest.of(page, limit, Sort.by("title")), posts.size());
        Mockito.when(postRepository.findAll(PageRequest.of(page, limit, Sort.by("title"))))
                .thenReturn(expectedPage);
        assertEquals(posts.size(), postService.getPostsByPage(page, limit).getTotalPages());
    }

    @Test
    public void testFindById() {
        Post post = posts.get(0);
        Long id = post.getId();
        Mockito.when(postRepository.findById(id))
                .thenReturn(Optional.of(post));
        assertEquals(post, postService.findById(id));
    }

    @Test
    public void testFindByIdThrowsNotFound() {
        Mockito.when(postRepository.findById(999L))
                .thenReturn(Optional.ofNullable(null));
        assertThrows(NotFoundException.class, () -> {
            postService.findById(999L);
        });
    }

    @Test
    public void testSearchPosts() {
        String param = "libero illum";
        String wrongParam = "thisisastringnobodywouldeveruse,probably@!#$@$";
        Mockito.when(postRepository.findByTitleContaining(param))
                .thenReturn(posts);
        assertTrue(postService.searchPosts(param).get(0).getTitle().contains(param));
        assertTrue(postService.searchPosts(wrongParam).isEmpty());
    }
}