package com.blog.app.service;

import com.blog.app.model.Comment;
import com.blog.app.model.CommentDTO;
import com.blog.app.model.Post;
import com.blog.app.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentService commentService;
    private List<Comment> comments = new ArrayList<>();

    @BeforeEach
    void setUp() {
        Post post = Post.builder().id(1L).build();
        Comment c1 = Comment.builder()
                .email("someone@email.com")
                .body("That looks great!")
                .name("Someone")
                .post(post)
                .id(1L)
                .build();
        Comment c2 = Comment.builder()
                .email("someoneElse@email.com")
                .body("That looks more than great!")
                .name("Someone Else")
                .post(post)
                .id(2L)
                .build();
        comments = new ArrayList<>();
        comments.add(c1);
        comments.add(c2);
    }

    @Test
    void testSave() {
        Comment c = comments.get(0);
        Mockito.when(commentRepository.save(Mockito.any(Comment.class)))
                .thenReturn(c);
        commentRepository.save(c);
        Mockito.verify(commentRepository).save(Mockito.any(Comment.class));
    }

    @Test
    void testSaveAll() {
        commentService.save(comments);
        Mockito.verify(commentRepository).saveAll(Mockito.anyList());
    }

    @Test
    void findByPostId() {
        Long id = comments.get(0).getPost().getId();
        Mockito.when(commentRepository.findAllByPostId(id)).thenAnswer((InvocationOnMock invocation) -> {
                    return comments;
                });
        List<CommentDTO> commentDTOs = comments.stream().map(CommentDTO::new).toList();
        assertEquals(commentDTOs, commentService.findByPostId(id));
        Mockito.verify(commentRepository).findAllByPostId(id);

        Mockito.when(commentRepository.findAllByPostId(id)).thenAnswer((InvocationOnMock invocation) -> new ArrayList<>());
        assertTrue(commentService.findByPostId(id).isEmpty());
    }
}