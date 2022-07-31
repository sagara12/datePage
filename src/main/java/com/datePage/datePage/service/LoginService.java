package com.datePage.datePage.service;

import com.datePage.datePage.request.domain.Post;
import com.datePage.datePage.repository.PostRepository;
import com.datePage.datePage.request.PostCreate;
import com.datePage.datePage.request.domain.Write;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class LoginService {

    private final PostRepository postRepository;

    public String write(PostCreate postCreate) {
        Post post = Post.builder()
                        .id(postCreate.getId())
                        .password(postCreate.getPassword())
                        .build();

        postRepository.save(post);
        return post.getId();

    }

    public LoginService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post get(String id) {

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글립니다"));

        return post;
    }
}
