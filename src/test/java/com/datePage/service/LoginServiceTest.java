package com.datePage.service;

import com.datePage.repository.PostRepository;
import com.datePage.request.domain.Post;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PostRepository postRepository;


    @BeforeEach
    void clean() {
        postRepository.deleteAll();
    }

    @Test
    @DisplayName("로그인 기능")
    void test2() {
        //given
        Post Idconfirm = Post.builder()
                .id("id")
                .password("password")
                .build();

        postRepository.save(Idconfirm);


        Long writeId = 1L;

        //when
       Post post = loginService.get(Idconfirm.getId());

        //then
        assertNotNull(post);
        assertEquals(1L, postRepository.count());
        assertEquals("id", post.getId());
        assertEquals("password", post.getPassword());

    }

}