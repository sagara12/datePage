package com.datePage.datePage.service;

import com.datePage.datePage.domain.Post;
import com.datePage.datePage.repository.PostRepository;
import com.datePage.datePage.request.PostCreate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginService {

    private final PostRepository postRepository;

    public void write(PostCreate postCreate) {
        Post post = new Post(postCreate.getId(), postCreate.getPassword());
        postRepository.save(post);

    }

    public LoginService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
}
