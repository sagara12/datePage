package com.datePage.datePage.repository;

import com.datePage.datePage.request.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
